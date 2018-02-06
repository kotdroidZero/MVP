package com.error_found.kotdroid.mvp.models.interactors;

import android.content.Context;

import com.error_found.kotdroid.mvp.models.DatabaseModel;
import com.error_found.kotdroid.mvp.models.pojos.User;
import com.error_found.kotdroid.mvp.presenters.DatabaseCallBack;

/**
 * Created by user12 on 6/2/18.
 */

public class SignupInteractor {
    User user;
    DatabaseModel model;

    public SignupInteractor() {
        user=new User();
    }

    public void updateDatabase(String name, String email, String password, String contact,
                               DatabaseCallBack callBack, Context context)
    {
        user._contact_no=contact;
        user._email_id=email;
        user._name=name;
        user._password=password;
        model=new DatabaseModel(context);

        long id=model.registerUser(user);
        if (id>0)
        {
            callBack.onSuccess();
        }
        else if (id==-100)callBack.onFailure(-100);

    }
}
