package com.error_found.kotdroid.mvp.presenters;

import android.util.Patterns;

import com.error_found.kotdroid.mvp.models.interactors.LoginInteractor;
import com.error_found.kotdroid.mvp.models.pojos.User;
import com.error_found.kotdroid.mvp.views.interfaces.LoginView;

/**
 * Created by user12 on 6/2/18.
 */

public class LoginPresenter {
    LoginView loginView;
    LoginInteractor loginInteractor;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractor();
    }

    public void loginUser(String email, String password) {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginView.emailError("invalid email");
        } else if (password.isEmpty()) {
            loginView.passwordError("not secured");
        } else {
            loginInteractor.authenticateAndLogin(email, password, loginView.getContext()
                    , new DatabaseCallBack() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onFailure(long id) {

                        }

                        @Override
                        public void loginSuccess(String name,String email,String contact) {
                            loginView.loggedIn(name,email,contact);
                        }

                        @Override
                        public void errorInLogin() {

                        }
                    });
        }
    }


}
