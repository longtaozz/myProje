package com.m.lib_http.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.m.lib_http.constants.BaseData;
import com.m.lib_http.bean.BaseResult;
import com.m.lib_http.listener.IResponseListener;
import com.m.lib_http.util.AndroidUtil;
import com.m.lib_http.util.NetConnect;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    public static final String CACHE_NAME = "App";
    private static final int DEFAULT_CONNECT_TIMEOUT = 30;
    private static final int DEFAULT_WRITE_TIMEOUT = 30;
    private static final int DEFAULT_READ_TIMEOUT = 30;
    public static String BASE_IP = BaseData.BASE_IP;
    private static OkHttpClient.Builder okHttpBuilder;
    private static HttpManager httpManager;
    private Retrofit retrofit;

    //构造方法私有
    private HttpManager(final Context context) {
        //手动创建一个OkHttpClient并设置超时时间
        okHttpBuilder = new OkHttpClient.Builder();
        /**
         * 设置缓存
         */
        File cacheFile = new File(context.getExternalCacheDir(), CACHE_NAME);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetConnect.isConnected(context)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (!NetConnect.isConnected(context)) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader(CACHE_NAME)// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader(CACHE_NAME)
                            .build();
                }
                return response;
            }
        };
        okHttpBuilder.cache(cache).addInterceptor(cacheInterceptor);


        /**
         * 设置头信息
         */
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .method(originalRequest.method(), originalRequest.body());
                requestBuilder.addHeader("x-token", BaseData.TOKEN);//添加请求头信息，服务器进行token有效性验证
                Request request = requestBuilder.build();

                //如果没有token获取token，按具体需求来
                if (TextUtils.isEmpty(BaseData.TOKEN)) {
                    Response response = chain.proceed(request);
                    List<String> strList = response.headers("x-token");
                    if (strList != null && strList.size() > 0) {
                        BaseData.TOKEN = strList.get(0);
                    }
                }

                return chain.proceed(request);
            }
        };
        okHttpBuilder.addInterceptor(headerInterceptor);


        //设置是否打印log
        Log.e("daying,..", AndroidUtil.isApkInDebug(context) + "");
        if (AndroidUtil.isApkInDebug(context)) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("cuowu......",message);
                    Logger.e(message);
                }


            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            okHttpBuilder.addInterceptor(loggingInterceptor);
        }

        /**
         * 设置超时和重新连接
         */
        okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);

        //添加证书
        SSLContext sslContext = getSSLContext(context);
        okHttpBuilder.sslSocketFactory(createSSLSocketFactory());
//        okHttpBuilder.sslSocketFactory(createSSLSocketFactory(),new TrustAllCerts());

        //错误重连
        okHttpBuilder.retryOnConnectionFailure(true);

    }

    //获取单例
    public static HttpManager getInstance(Context context) {
        if (httpManager == null) {
            httpManager = new HttpManager(context);
        }
        return httpManager;
    }

    /**
     * 证书，https请求
     *
     * @param context
     * @return
     */
    private static SSLContext getSSLContext(Context context) {
        SSLContext s_sSLContext;
        try {
            s_sSLContext = SSLContext.getInstance("TLS");
            //信任所有证书 （官方不推荐使用）
            s_sSLContext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {

                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                }
            }}, new SecureRandom());
            return s_sSLContext;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> void request(Observable<BaseResult<T>> observable,
                            final IResponseListener<T> listener) {
        if (httpManager == null)
            return;
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<T>>() {

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Log.e("onError..........", e.getMessage());
                                if (listener != null) {
                                    listener.onFail();
                                }
                            }

                            @Override
                            public void onComplete() {//请求完成
                                Log.e("onComplete..........", "onComplete");
                                listener.onComplete();
                            }

                            @Override
                            public void onSubscribe(Disposable disposable) {
                                Log.e("onSubscribe..........", "onSubscribe");

                            }

                            @Override
                            public void onNext(BaseResult<T> data) {
                                Log.e("onNext..........", data.getMessge());
                                if (listener != null) {
                                    //假如为token超时或无token（由服务器定义）
                                    if (data.getCode() == 10003) {
                                        //跳回登录页
                                        return;
                                    }
                                    listener.onSuccess(data.getData());
                                }
                            }
                        }
                );
    }

    public <T> T createApi(Class<T> service) {
        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())//json转换成JavaBean
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_IP)
                .build();
        return retrofit.create(service);
    }


    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     *
     * @return
     */
    public SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }

    /**
     * 用于信任所有证书
     */
    class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

}
