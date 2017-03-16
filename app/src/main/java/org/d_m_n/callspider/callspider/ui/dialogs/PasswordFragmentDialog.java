package org.d_m_n.callspider.callspider.ui.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.managers.PreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dmytro.radchenko on 3/16/2017.
 */

public class PasswordFragmentDialog extends DialogFragment {



    public enum Status{
        CANCELED, OK, ERROR, EMPTY;
    }


    public interface PasswordEnterCallback{

        void onEnterPassword(Status status, String comment, DialogInterface dialog);
    }

    private PasswordEnterCallback mCallback;

    @BindView(R.id.et_dlg_passsword)
    EditText mEtPassword;

    public static PasswordFragmentDialog newInstance(PasswordEnterCallback callback){
        PasswordFragmentDialog d = new PasswordFragmentDialog(callback);
        return d;
    }

    private PasswordFragmentDialog(){

    }

    private PasswordFragmentDialog(PasswordEnterCallback callback){
        mCallback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dlg_password, container, true);
        ButterKnife.bind(this, rootView);
        return  rootView;
    }

    @OnClick(R.id.btn_dlg_password_ok)
    void onPasswordClickOK(){
        if (TextUtils.isEmpty(mEtPassword.getText()) && !PreferencesManager.isPasswordSet()){
            callbackPasswordStatus(Status.EMPTY, null);
            return;
        }
        if (isPasswordOk(PasswordFragmentDialog.this.getActivity(),String.valueOf(mEtPassword.getText()))){
            callbackPasswordStatus(Status.OK, null);
            this.dismissAllowingStateLoss();
            return;
        } else {
            callbackPasswordStatus(Status.ERROR,"Smth wrong");
        }
    }

    @OnClick(R.id.btn_dlg_password_cancel)
    void onPasswordClickCancel() {
       callbackPasswordStatus(Status.CANCELED, null);
        this.dismissAllowingStateLoss();
    }


    private void callbackPasswordStatus(Status status, String comment) {
        if (mCallback != null){
            mCallback.onEnterPassword(status, comment, PasswordFragmentDialog.this.getDialog());
        }
    }

    private boolean isPasswordOk(Context ctxt, String password) {
        if (!PreferencesManager.isPasswordSet()){
            callbackPasswordStatus(Status.ERROR, ctxt.getResources().getString(R.string.password_not_set));
            return false;
        }
        return true;
    }
}
