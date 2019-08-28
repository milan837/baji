package com.example.baji.BaseClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.File;

public class BaseFragment extends Fragment {
    private ProgressDialog dialog;

    public void preventScreenCapture() {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    public void launchActivity(Activity context, Class activityClass, Bundle bundle, boolean finish) {
        Intent intent = new Intent(context, activityClass);

        intent.putExtras(bundle != null ? bundle : new Bundle());
        startActivity(intent);
        if (finish) {
            context.finish();
        }
    }

    public void hideKeyboard() {
        try {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_FORCED);
        }
    }

    public String getFieldText(EditText editText) {
        return editText.getText().toString().trim();
    }

    // Intent Methods
    public void simpleIntent(Activity activity, Class activityClass) {
        Intent intent = new Intent(activity, activityClass);
        startActivity(intent);
    }

    public void stackClearIntent(Activity activity, Class activityClass) {
        Intent intent = new Intent(activity, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void stackClearIntentWithBundle(Activity activity, Class activityClass, Bundle bundle) {
        Intent intent = new Intent(activity, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void stackClearIntentFinish(Activity activity, Class activityClass) {
        Intent intent = new Intent(activity, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    public void finishIntent(Activity activity, Class activityClass, Bundle bundle) {
        Intent intent = new Intent(activity, activityClass);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().finish();

    }

    public void finishIntentBundle(Activity activity, Class activityClass, Bundle bundle) {
        Intent intent = new Intent(activity, activityClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //Show Toast Methods
    public void showLongToast(Activity activity, String massage) {
        Toast.makeText(activity, massage, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(Activity activity, String massage) {
        Toast.makeText(activity, massage, Toast.LENGTH_SHORT).show();
    }

    /**
     * show progress dialog for api calls
     */
    public void showProgress() {
        try {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            if (dialog != null && dialog.isShowing()) {
                return;
            } else {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * dismiss progress dialog after api calls
     */
    public void dismissProgress() {
        try {
            if (dialog != null) {
                dialog.cancel();
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // <uses-permission
        // android:name="android.permission.CLEAR_APP_CACHE"></uses-permission>
        // The directory is now empty so delete it

        return dir.delete();
    }
}
