package com.example.android_ket_cau_soft.view.fragment;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.clientservice.ClientService;
import com.example.android_ket_cau_soft.api.request.CheckTokenReques;
import com.example.android_ket_cau_soft.api.response.setting.CheckTokenResponse;
import com.example.android_ket_cau_soft.broadcastreceiver.MyBroadCast;
import com.example.android_ket_cau_soft.callback.IMainCallBack;
import com.example.android_ket_cau_soft.callback.OnAPICallback;
import com.example.android_ket_cau_soft.callback.OnCheckingCallback;
import com.example.android_ket_cau_soft.callback.OnParentFrgCallback;
import com.example.android_ket_cau_soft.callback.OnTouchListener;
import com.example.android_ket_cau_soft.callback.OnUpdateCountCallback;
import com.example.android_ket_cau_soft.callback.TextChangeListener;
import com.example.android_ket_cau_soft.sharepreference.CustomSharePreference;
import com.example.android_ket_cau_soft.view.dialog.CustomProgressDialog;
import com.example.android_ket_cau_soft.view.dialog.NoticeDialog;
import com.example.android_ket_cau_soft.view.fragment.login.LoginFragment;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;

public abstract class BaseFragment<T extends ViewBinding, M extends BaseVM> extends Fragment implements View.OnClickListener, OnCheckingCallback, OnAPICallback, OnUpdateCountCallback, MyBroadCast.OnNetworkCallback {

    protected static final String NETWORK_ER_MSG = "Kết nối mạng không ổn định, vui lòng thử lại";
    private static final String TAG = BaseFragment.class.getName();
    protected T mBinding;
    protected M mViewModel;
    protected Context mContext;
    protected Object mData;
    private static CustomProgressDialog dialog;
    private static NoticeDialog noticeDialog;
    protected IMainCallBack mainCallBack;
    protected OnParentFrgCallback onParentFrgCallback;
    protected OnUpdateCountCallback onUpdateCountCallback;
    protected OnTouchListener onTouchListener;
    protected final Handler handler = new Handler();
    protected MyBroadCast myBroadCast = new MyBroadCast();
    protected IntentFilter intentFilter;

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    public void setOnUpdateCountCallback(OnUpdateCountCallback onUpdateCountCallback) {
        this.onUpdateCountCallback = onUpdateCountCallback;
    }

    public void setOnParentFrgCallback(OnParentFrgCallback onParentFrgCallback) {
        this.onParentFrgCallback = onParentFrgCallback;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (myBroadCast != null) {
            mContext.unregisterReceiver(myBroadCast);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (myBroadCast != null && intentFilter != null) {
            mContext.registerReceiver(myBroadCast, intentFilter);
        }


    }

    protected static NoticeDialog getNoticeDialog(Context mContext) {
        if (noticeDialog == null) {
            noticeDialog = new NoticeDialog(mContext);
        }
        return noticeDialog;
    }
//
//    protected static void updateNoticeDialog(String title, String content, String btPositive, String btNegative,
//                                      boolean isCancelable, View.OnClickListener onClickListener) {
//
//   noticeDialog.setUpDialog(title, content, btPositive, btNegative, isCancelable, onClickListener);
//
//    }

    protected static void showNoticeDialog() {

        if (!noticeDialog.isShowing()) {
            noticeDialog.show();

        }
    }

    protected void dismissNoticeDialog() {

        if (noticeDialog.isShowing()) {

            noticeDialog.dismiss();
        }

    }

    private CustomProgressDialog getProgressDialog() {
        if (dialog == null) {
            dialog = new CustomProgressDialog(mContext);
        }
        return dialog;

    }

    protected void dismissProgressDialog() {

        if (getProgressDialog().isShowing()) {
            getProgressDialog().dismiss();
        }
    }

    protected void showProgressDialog() {

        if (!getProgressDialog().isShowing()) {
            getProgressDialog().show();

        }
    }


    public void onFocusChange(TextInputLayout textField, TextInputEditText edt, int endIcon, MaterialButton button) {

        edt.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    textField.setEndIconMode(endIcon);
                    textField.setError(null);


                } else {
                    textField.setEndIconMode(TextInputLayout.END_ICON_NONE);

                }
            }
        });

    }


    private void onChangeListen(TextInputLayout textField, TextInputEditText edt, int endIcon) {


    }


    public void setmData(Object mData) {
        this.mData = mData;
    }

    public void setMainCallBack(IMainCallBack mainCallBack) {
        this.mainCallBack = mainCallBack;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public  View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = initViewBinding(inflater);
        mViewModel = new ViewModelProvider(this).get(getClassVM());
        mViewModel.setApiCallback(this);
        mViewModel.setOnCheckingCallback(this);
        return mBinding.getRoot();
    }


    protected abstract Class<M> getClassVM();

    protected abstract T initViewBinding(LayoutInflater inflater);

    @Override
    public  void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkNetworkConnection();
        initView();
    }


    protected void checkNetworkConnection() {
        MyBroadCast.onNetworkCallback = this;

        intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(myBroadCast, intentFilter);


    }

    @Override
    public void returnNetworkState(boolean state) {
        if (state) {
            onCallbackSuccess(EnumStorage.NETWORK_STATE.getEnumValue(), "state", null);
        } else {
            onCallbackError(EnumStorage.NETWORK_STATE.getEnumValue(), NETWORK_ER_MSG);
        }
    }

    protected abstract void initView();

    @Override
    public final void onClick(View v) {
        clickView(v);
    }

    protected void clickView(View v) {
    }

    protected boolean checkValidateEmail(String textEmail) {
        return !TextUtils.isEmpty(textEmail) && Patterns.EMAIL_ADDRESS.matcher(textEmail).matches();
    }

    //check valid email
//    protected ObjectResult checkEmail(String email) {
//
//
//        boolean state = (email.trim()).endsWith(EnumCustom.EMAIL_PATH.getEnumValue());
//
//        if (state) {
//
//            if (!checkValidateEmail(email)) {
//
//                Log.e(TAG, "login_frg: check_valid_email:   Invalid  email1  : " + email);
//
//                return new ObjectResult(false, null);
//
//
//            } else {
//                Log.e(TAG, "login_frg: check_valid_email:  Valid email   : " + email);
//
//                return new ObjectResult(true, email);
//
//            }
//
//
//        } else {
//
//
//            if (!checkValidateEmail(email + EnumCustom.EMAIL_PATH.getEnumValue())) {
//
//                Log.e(TAG, "login_frg: check_valid_email 2:  invalid  email  : " + email);
//
//                return new ObjectResult(false, null);
//
//            } else {
//
//                return new ObjectResult(true, email + EnumCustom.EMAIL_PATH.getEnumValue());
//
//
//            }
//
//        }
//    }

    protected void showError(TextInputLayout textView, String msg) {
        textView.setErrorEnabled(true);
        textView.setError(msg);
    }

    protected void removeError(TextInputLayout textView) {
        textView.setError(null);
    }


    protected void saveToPreference(String key, String stringValue) {
        CustomSharePreference.getInstance().saveStringValue(key, stringValue);
    }

    protected void saveToPreference(String key, boolean booleanValue) {
        CustomSharePreference.getInstance().saveBooleanValue(key, booleanValue);

    }

    protected Boolean getPreferenceBooleanValue(String key) {
        return CustomSharePreference.getInstance().getBooleanValue(key);

    }

    protected String getPreferenceStringValue(String key) {
        return CustomSharePreference.getInstance().getStringValue(key);

    }

//    protected void handleActivityCallback(String key) {
//        mainCallBack.changeActivity(key);
//    }

    protected void showSnackbar(View view, String mesage, boolean isError) {

        Snackbar snackbar =
                Snackbar.make(view, mesage, isError ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT);

        snackbar.setAction("Ẩn", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        if (isError) {
            snackbar.setBackgroundTint(getResources().getColor(R.color.red, null));
        } else {
            snackbar.setBackgroundTint(getResources().getColor(R.color.green, null));
        }
        snackbar.setActionTextColor(getResources().getColor(R.color.black, null));

        snackbar.setTextColor(getResources().getColor(R.color.white, null));

        snackbar.show();
    }

    //hide soft keyboard
    protected void hideSoftKeboard() {
        View v = requireActivity().getCurrentFocus();
        if (v != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            v.clearFocus();
        }
    }

    //show soft keyboard
    protected void showSoftKeyboard(TextInputEditText view) {
        // hideSoftKeboard();
        //show keyboard
        view.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);


    }

    //remove focus on edittext
    protected void removeFocusOnView(View edt) {
        if (edt.isFocused()) {
            edt.clearFocus();
        }
    }

    //edittext action listener
    protected void setOnEditorActionListener(TextInputEditText view) {

        view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                if (v.equals(view)) {
                    onAction(v, actionId);
                    return true;
                }

                return false;

            }
        });
    }

    protected void onAction(TextView v, int actionId) {
        //do somthing
    }

    //back to previous fragment
    protected void backToPreviousFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
    }

    @Override
    public void onCallbackError(String key, String msg) {
        dismissProgressDialog();
        if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {

            if (msg.equals("401")) {
                getNoticeDialog(mContext).setUpDialog(" Thông báo", "Tài khoản đã được đăng nhập ở nơi khác, vui lòng đăng nhập lại", "Ok", null, false, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dismissNoticeDialog();
                        onParentFrgCallback.showFragmentFromMenu(LoginFragment.TAG, null, false);
                        CustomSharePreference.getInstance().saveBooleanValue(CustomSharePreference.LOGIN_STATE, false);
                    }
                });
                showNoticeDialog();

            } else {
                onCallbackError(key, "OOPS!!!: Đã có lỗi gì đó xảy ra");
            }

        }

    }

    protected void returnError(String key, String msg) {
//
    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        dismissProgressDialog();

    }


    @Override
    public void handleAPIFail(String key, int code, String msg) {
        dismissProgressDialog();

    }

    @Override
    public void handleAPISuccess(String key, String msg) {

    }

    @Override
    public void updateCount(int count) {

    }
}
