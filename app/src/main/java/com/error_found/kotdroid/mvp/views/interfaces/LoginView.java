package com.error_found.kotdroid.mvp.views.interfaces;

import com.error_found.kotdroid.mvp.models.pojos.User;

/**
 * Created by user12 on 6/2/18.
 */

public interface LoginView extends BaseView{
    void loggedIn(String name,String email,String contact);
    void loginFailed();

}
