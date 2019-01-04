package com.app.lib_common.okhttp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * http请求封装
 * Created by Administrator on 2018/4/12.
 */

public class OkHttpUtil {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private static final byte[] LOCKER = new byte[0];
    private static OkHttpUtil mInstance;
    private OkHttpClient mOkHttpClient;

    protected OkHttpUtil() {
        OkHttpClient.Builder ClientBuilder = new OkHttpClient.Builder();
        ClientBuilder.readTimeout(20, TimeUnit.SECONDS);//读取超时
        ClientBuilder.connectTimeout(6, TimeUnit.SECONDS);//连接超时
        ClientBuilder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        //支持HTTPS请求，跳过证书验证
        ClientBuilder.sslSocketFactory(createSSLSocketFactory());
        ClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        mOkHttpClient = ClientBuilder.build();
    }

    /**
     * 单例模式获取NetUtils
     *
     * @return
     */
    public static OkHttpUtil getInstance() {
        if (mInstance == null) {
            synchronized (LOCKER) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * get请求，同步方式，获取网络数据，是在主线程中执行的，需要新起线程，将其放到子线程中执行
     *
     * @param url
     * @return
     */
    public Response getDataSyn(String url) {
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        //2 将Request封装为Call
        Call call = mOkHttpClient.newCall(request);
        //3 执行Call，得到response
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * post请求，同步方式，提交数据，是在主线程中执行的，需要新起线程，将其放到子线程中执行
     *
     * @param url
     * @param bodyParams
     * @return
     */
    public Response postDataSynToNet(String url, Map<String, Object> bodyParams) {
        //1构造RequestBody
        RequestBody body = setRequestBody(bodyParams);
        //2 构造Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).url(url).build();
        //3 将Request封装为Call
        Call call = mOkHttpClient.newCall(request);
        //4 执行Call，得到response
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    /**
     * 异步get请求
     * @param url
     * @param headers    请求头
     * @param callback      回调
     */
    public void getDataAsyn(String url, Map<String, Object> headers,Callback callback) {
        //1 构造Request
        Log.d("URLxxxxxxxxxx", url);
        // 以 Entry 添加消息头
        Request.Builder builder = new Request.Builder();
        if (headers.size() > 0) {
            Iterator iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                builder.header((String) entry.getKey(), (String) entry.getValue());
            }
        }
        Request request = builder
                .get()
                .url(url)
                .build();
        //2 将Request封装为Call
        Call call = mOkHttpClient.newCall(request);
        //3 执行Call
        call.enqueue(callback);
    }



    /**
     * post请求，异步方式，提交数据，是在子线程中执行的，需要切换到主线程才能更新UI
     *
     * @param url
     * @param bodyParams
     * @param callback
     */
    public void postDataAsynFrom(String url, Map<String, Object> bodyParams,Map<String, Object> headers,Callback callback) {
        String json = gson.toJson(bodyParams);
        Log.e("json", json.toString());
        Log.e("jsonURL", url);
        //1构造RequestBody
        RequestBody body = setRequestBody(bodyParams);
        //2 构造Request
        // 以 Entry 添加消息头
        Request.Builder builder = new Request.Builder();
        if (headers.size() > 0) {
            Iterator iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                builder.header((String) entry.getKey(), (String) entry.getValue());
            }
        }
        Request request = builder.post(body).url(url).build();
        //3 将Request封装为Call
        Call call = mOkHttpClient.newCall(request);
        //4 执行Call
        call.enqueue(callback);
    }


    // 状态码
//            200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的。
//            201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
//            202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
//            204 NO CONTENT - [DELETE]：用户删除数据成功。
//            400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务		器没有进行新建或修改数据的操作，该操作是幂等的。
//            401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
//            403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被		禁止的。
//            404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有		进行操作，该操作是幂等的。
//            406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON		格式，但是只有XML格式）。
//            410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
//            422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一		个验证错误。
//            500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出		的请求是否成功。

    /**
     * post异步传递json参数请求
     *
     * @param url
     * @param bodyParams
     * @param callback
     */
    public void postDataAsynJson(String url, Map<String, Object> bodyParams,Map<String, Object> headers,Callback callback) {
        String json = gson.toJson(bodyParams);
        Log.e("json", json.toString());
        Log.e("jsonURL", url);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        // 以 Entry 添加消息头
        Request.Builder builder = new Request.Builder();
        if (headers.size() > 0) {
            Iterator iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                builder.header((String) entry.getKey(), (String) entry.getValue());
            }
        }
        Request request = builder
                .post(requestBody).url(url).build();

        //3 将Request封装为Call
        Call call = mOkHttpClient.newCall(request);
        //4 执行Call
        call.enqueue(callback);
    }


    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("application/octet-stream");

    /**
     * 上传多文件及参数
     *
     * @param reqUrl URL地址
     * @param params 参数
     * @param files  文件路径
     */
    public void postUoladFile(String reqUrl, Map<String, String> params, List<File> files, Map<String, Object> headers,Callback callback) {
        Log.e("json", gson.toJson(params));
        Log.e("jsonURL", reqUrl);

        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        multipartBodyBuilder.setType(MultipartBody.FORM);
        // 遍历map中所有参数到builder
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                multipartBodyBuilder.addFormDataPart(key, params.get(key));
            }
        }
        // 遍历paths中所有文件绝对路径到builder，并约定key如“upload”作为后台接受多张图片的key
        if (files.size() > 0) {
            for (File file : files) {
                if (file.exists()) {
                    multipartBodyBuilder.addFormDataPart("files", file.getName(),
                            RequestBody.create(MEDIA_TYPE_PNG, file));
                }
            }
        }
        // 构建请求体
        RequestBody requestBody = multipartBodyBuilder.build();

        // 以 Entry 添加消息头
        Request.Builder builder = new Request.Builder();
        if (headers.size() > 0) {
            Iterator iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                builder.header((String) entry.getKey(), (String) entry.getValue());
            }
        }
        builder.url(reqUrl);// 添加URL地址
        builder.post(requestBody);
        Request request = builder.build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * post的请求参数，构造RequestBody
     *
     * @param BodyParams
     * @return
     */
    private RequestBody setRequestBody(Map<String, Object> BodyParams) {
        RequestBody body = null;
        FormBody.Builder formEncodingBuilder = new FormBody.Builder();
        if (BodyParams != null) {
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                Log.e("key", key);
                formEncodingBuilder.add(key, BodyParams.get(key) == null ? "" : BodyParams.get(key).toString());
                Log.d("post http", "post_Params===" + key + "====" + BodyParams.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;

    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            //如果仅仅是用来判断网络连接
            //则可以使用cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
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
