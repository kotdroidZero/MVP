package com.error_found.kotdroid.mvp.views.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.error_found.kotdroid.mvp.R;
import com.error_found.kotdroid.mvp.presenters.SignUpPresenter;
import com.error_found.kotdroid.mvp.views.interfaces.SignUpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user12 on 6/2/18.
 */

public class SignUpFragment extends Fragment implements SignUpView{

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_contact)
    EditText etContact;
    SignUpPresenter signUpPresenter;

    FragmentManager manager;
    FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_signup,container,false);
        init(view);
        return view;
    }

    private void init(View view) {
        ButterKnife.bind(this,view);
        signUpPresenter=new SignUpPresenter(this);
        manager=getFragmentManager();
        transaction=manager.beginTransaction();

    }

    @OnClick(R.id.btn_signup)
    public void signupClick()
    {
        signUpPresenter.signUp(etName.getText().toString().trim(),etEmail.getText()
        .toString().trim(),etPassword.getText().toString().trim(),
                etContact.getText().toString().trim());
    }


    @OnClick(R.id.tv_login)
    public void loginClick()
    {
        transaction.replace(R.id.fl,new LoginFragment())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void registered() {


        Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();

        transaction.remove(this)
                .replace(R.id.fl,new LoginFragment())
                .setReorderingAllowed(true)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    @Override
    public void registrationFailed( ) {
            Toast.makeText(getActivity(),
                    "Registration failed ! Please make another attempt.",
                    Toast.LENGTH_SHORT).show();

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
        etName.setError(err);
    }

    @Override
    public void contactError(String err) {
        etContact.setError(err);
    }

    @Override
    public void alreadyRegistered() {
        Toast.makeText(getActivity(),
                "you are already on our Database!",
                Toast.LENGTH_SHORT).show();
    }

}
