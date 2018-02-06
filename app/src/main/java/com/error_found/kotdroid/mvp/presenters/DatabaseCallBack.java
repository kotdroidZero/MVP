package com.error_found.kotdroid.mvp.presenters;

import com.error_found.kotdroid.mvp.models.pojos.User;

/**
 * Created by user12 on 6/2/18.
 */

public interface DatabaseCallBack {
    void onSuccess();
    void onFailure(long id);
    void loginSuccess(String name,String email,String password);
    void errorInLogin();
}
