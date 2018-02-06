package com.error_found.kotdroid.mvp.views.interfaces;

import android.content.Context;

/**
 * Created by user12 on 6/2/18.
 */

public interface SignUpView extends BaseView{
    void registered();
    void registrationFailed();
    void alreadyRegistered();
}
