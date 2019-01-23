package com.elegion.myfirstapplication.utils;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

public class Utils {

    public static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordsValid(String password) {
        return !TextUtils.isEmpty(password);
    }

    public static boolean isPasswordsValid(String password, String passwordAgain) {
        return password.equals(passwordAgain)
                && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(passwordAgain);
    }

    public static void showMessage(@StringRes int string, Activity activity) {
        Toast.makeText(activity, activity.getResources().getString(string), Toast.LENGTH_LONG).show();
    }
}
