package com.error_found.kotdroid.mvp.views.interfaces;

import android.content.Context;

/**
 * Created by user12 on 6/2/18.
 */

public interface BaseView {
    void emailError(String err);
    void passwordError(String err);
    void nameError(String err);
    void contactError(String err);
    Context getContext();
}
