package com.my.base.model.contract;


import com.app.lib_common.structure.IPView;
import com.my.base.model.bean.CopyUser;

import java.util.List;

/**
 *    time   : 2018/11/17
 *    desc   : 可进行拷贝的契约类
 */
public class CopyUserContract {

    /**
     * 这里的 IPView 可继承可不继承，看实际情况而定
     */
    public interface View extends IPView{

        void loginError(String msg);

        void loginSuccess(CopyUser data);
    }

    public interface Presenter {
        void login(String account, String password);
    }
}