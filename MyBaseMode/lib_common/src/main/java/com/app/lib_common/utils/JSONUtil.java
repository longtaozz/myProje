package com.app.lib_common.utils;

import android.se.omapi.Reader;

import com.app.lib_common.bean.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * json解析工具类
 *
 * @author lt
 * @time 2018/12/11 10:38
 **/
public class JSONUtil {
    public static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    //对象转json字符串
    public static String objectToStr(Object object) {
        return gson.toJson(object);
    }

    //字符串转List
    public static <T>Result<List<T>> strToList(String str, Class<T> clazz) {
        // 生成List<T> 中的 List<T>
        Type listType = new MyParameterizedType(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new MyParameterizedType(Result.class, new Type[]{listType});
        return gson.fromJson(str, type);
    }

    public static <T>Result<T> strToObject(String str, Class<T> clazz){
        //生成type
        Type type = new MyParameterizedType(Result.class, new Class[]{clazz});
        return gson.fromJson(str,type);
    }

    /**
     * 通過key获取Object
     * @return
     * @throws JSONException
     */
    public static Object getObjectKey(String json,String key) throws JSONException {
        JSONObject jsonObject=new JSONObject(json);
        return jsonObject.get(key);
    }
}
