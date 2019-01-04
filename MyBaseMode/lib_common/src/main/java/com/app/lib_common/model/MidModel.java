package com.app.lib_common.model;


import com.app.lib_common.structure.PModel;
/**
* model路由
* @author lt
* @time 2018/12/11 9:04
*
**/
public class MidModel {

    public static PModel request(String modleUrl){
        PModel pModel = null;
        try {
            //利用反射机制获得对应Model对象的引用
            pModel = (PModel)Class.forName(modleUrl).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return pModel;
    }
}
