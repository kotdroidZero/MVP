package com.error_found.kotdroid.mvp.presenters;

import android.util.Patterns;

import com.error_found.kotdroid.mvp.models.interactors.SignupInteractor;
import com.error_found.kotdroid.mvp.models.pojos.User;
import com.error_found.kotdroid.mvp.views.interfaces.SignUpView;

import java.util.regex.Pattern;

/**
 * Created by user12 on 6/2/18.
 */

public class SignUpPresenter {
    SignUpView signUpView;
    SignupInteractor signupInteractor;

    public SignUpPresenter(SignUpView signUpView) {
        this.signUpView = signUpView;
        signupInteractor = new SignupInteractor();
    }

    public void signUp(String name, String email, String password, String contact) {
        if (name.isEmpty()) {
            signUpView.nameError("can not be empty");
        } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signUpView.emailError("invalid email");
        } else if (!Patterns.PHONE.matcher(contact).matches()) {
            signUpView.contactError("invalid contact");
        } else if (password.isEmpty()) {
            signUpView.passwordError("not secured");
        } else {
            signupInteractor.updateDatabase(name, email, password, contact,
                    new DatabaseCallBack() {
                        @Override
                        public void onSuccess() {
                            signUpView.registered();
                        }

                        @Override
                        public void onFailure(long id) {
                            if (id == -100) {
                                signUpView.alreadyRegistered();
                            } else signUpView.registrationFailed();
                        }

                        @Override
                        public void loginSuccess(String name, String email, String password) {

                        }


                        @Override
                        public void errorInLogin() {

                        }

                    }, signUpView.getContext());
        }
    }
}
