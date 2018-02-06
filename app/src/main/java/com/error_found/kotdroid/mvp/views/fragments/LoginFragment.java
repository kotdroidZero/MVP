package com.error_found.kotdroid.mvp.views.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.error_found.kotdroid.mvp.R;
import com.error_found.kotdroid.mvp.models.pojos.User;
import com.error_found.kotdroid.mvp.presenters.LoginPresenter;
import com.error_found.kotdroid.mvp.views.interfaces.LoginView;
import com.error_found.kotdroid.mvp.views.interfaces.SignUpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user12 on 6/2/18.
 */

public class LoginFragment extends Fragment implements LoginView{

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    FragmentManager manager;
    FragmentTransaction transaction;
    LoginPresenter loginPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);
        init(view);
        return view;
    }

    private void init(View view) {
        ButterKnife.bind(this,view);
        loginPresenter=new LoginPresenter(this);
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
    }

    @OnClick(R.id.btn_login)
    public void loginClick()
    {
        loginPresenter.loginUser(etEmail.getText().toString().trim(),
                etPassword.getText().toString().trim());
    }


    @OnClick(R.id.tv_signup)
    public void signupClick()
    {
        transaction.replace(R.id.fl,new SignUpFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                //.setReorderingAllowed(true)
                .commit();

    }


    @Override
    public void loggedIn(String name,String email,String contact) {
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        bundle.putString("email",email);
        bundle.putString("contact",contact);
        HomeFragment homeFragment=new HomeFragment();
        homeFragment.setArguments(bundle);


        //doubt
        //manager.popBackStack();
        transaction.remove(this)
                .replace(R.id.fl,homeFragment)
              //  .setReorderingAllowed(true)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void loginFailed() {
        Toast.makeText(getContext(), "login failed !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emailError(String err) {
        etEmail.setError(err);
    }

    @Override
    public void passwordError(String err) {
        etPassword.setError(err);
    }

    @Override
    public void nameError(String err) {

    }

    @Override
    public void contactError(String err) {

    }
}
