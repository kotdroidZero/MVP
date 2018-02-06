package com.error_found.kotdroid.mvp.models.interactors;

import android.content.Context;

import com.error_found.kotdroid.mvp.models.DatabaseModel;
import com.error_found.kotdroid.mvp.models.pojos.User;
import com.error_found.kotdroid.mvp.presenters.DatabaseCallBack;

/**
 * Created by user12 on 6/2/18.
 */

public class LoginInteractor {
    DatabaseModel model;


    public void authenticateAndLogin(String email, String password, Context context,
                                     DatabaseCallBack callBack) {
        model=new DatabaseModel(context);
        User user=model.loginUser(email,password);
        if (user!=null)
        {
            String name=user._name;
            String email1=user._email_id;
            String contact=user._contact_no;
            callBack.loginSuccess(name,email1,contact);
        }
        else
        {
            callBack.errorInLogin();
        }
    }
}
