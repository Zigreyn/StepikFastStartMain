package com.elegion.myfirstapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.elegion.myfirstapplication.R;
import com.elegion.myfirstapplication.utils.SharedPreferencesHelper;
import com.elegion.myfirstapplication.data.User;
import com.elegion.myfirstapplication.utils.Utils;

public class RegistrationFragment extends Fragment {

    private EditText mLogin;
    private EditText mPassword;
    private EditText mPasswordAgain;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    private View.OnClickListener mOnRegistrationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isInputValid()) {
                boolean isAdded = mSharedPreferencesHelper.addUser(new User(
                        mLogin.getText().toString(),
                        mPassword.getText().toString()));
                if (isAdded) {
                    Utils.showMessage(R.string.login_register_success, getActivity());
                    getFragmentManager().popBackStack();
                } else {
                    Utils.showMessage(R.string.login_busy, getActivity());
                }
            }else {
                Utils.showMessage(R.string.login_register_error, getActivity());
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        mLogin = view.findViewById(R.id.etLogin);
        mPassword = view.findViewById(R.id.etPassword);
        mPasswordAgain = view.findViewById(R.id.tvPasswordAgain);
        Button mRegistration = view.findViewById(R.id.btnRegistration);
        mRegistration.setOnClickListener(mOnRegistrationClickListener);
        return view;
    }

    private boolean isInputValid() {
        String email = mLogin.getText().toString();
        String password = mPassword.getText().toString();
        String passwordAgain = mPasswordAgain.getText().toString();
        return Utils.isEmailValid(email) && Utils.isPasswordsValid(password, passwordAgain);
    }
}
