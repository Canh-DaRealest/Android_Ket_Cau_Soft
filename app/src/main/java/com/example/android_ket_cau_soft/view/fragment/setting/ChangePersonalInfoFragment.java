package com.example.android_ket_cau_soft.view.fragment.setting;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.bumptech.glide.Glide;
import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.database.entities.User;
import com.example.android_ket_cau_soft.databinding.FragmentChangePersonalInfoBinding;
import com.example.android_ket_cau_soft.model.UserData;
import com.example.android_ket_cau_soft.sharepreference.CustomSharePreference;
import com.example.android_ket_cau_soft.view.activity.MainActivity;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.view.fragment.login.LoginFragment;
import com.example.android_ket_cau_soft.viewmodel.setting.ChangepersonalInfoVM;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChangePersonalInfoFragment extends BaseFragment<FragmentChangePersonalInfoBinding, ChangepersonalInfoVM> {


    public static final String TAG = ChangePersonalInfoFragment.class.getName();


    @Override
    protected Class<ChangepersonalInfoVM> getClassVM() {
        return ChangepersonalInfoVM.class;
    }

    @Override
    protected FragmentChangePersonalInfoBinding initViewBinding(LayoutInflater inflater) {
        return FragmentChangePersonalInfoBinding.inflate(inflater);
    }

    @Override
    protected void initView() {


        if (mViewModel.getAccount() != null) {
            Log.i(TAG, "initView: ChangePersonalInfo: " + mViewModel.getAccount().toString());
            setUiEdittext(mViewModel.getAccount());
        } else {
            mViewModel.updateAccountFromDB();
            setUiEdittext(mViewModel.getAccount());
            Log.e(TAG, "initView: " + "nulll");
        }
        onFocusChange(mBinding.textFieldUpdateName, mBinding.edtUpdateName, TextInputLayout.END_ICON_CLEAR_TEXT, mBinding.btSave);
        onFocusChange(mBinding.texfieldUpdatePhone, mBinding.edtUpdatePhone, TextInputLayout.END_ICON_CLEAR_TEXT, mBinding.btSave);


        clickItems();


    }

    private void setUiEdittext(User acount) {

        Glide.with(mContext).load(acount.getImage()).into(mBinding.cvPersonalImage);
        Log.e(TAG, "setUiEdittext: " + acount.getImage());
        mBinding.ivVipMember.setVisibility(acount.getVipMember() ? View.VISIBLE : View.GONE);
        mBinding.tvUserEmail.setText("Người dùng: " + acount.getEmail());
        mBinding.edtUpdateName.setText(acount.getName());
        mBinding.edtUpdatePhone.setText(acount.getSdt().equals("null") ? "" : acount.getSdt());
        mBinding.edtUpdateBirthday.setText(acount.getBirthday());

    }

    private void clickItems() {
        mBinding.btSave.setOnClickListener(this);

        mBinding.texfieldUpdateBirthday.setOnClickListener(this);
        mBinding.edtUpdateBirthday.setOnClickListener(this);
        mBinding.edtUpdateBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickerBottomSheet();
                }
            }
        });

    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);
        if (v.getId() == R.id.bt_save) {
            if (mViewModel.getState()) {
                doSavingInfo();
            } else {
                showSnackbar(mBinding.lnFrgSaveUser, NETWORK_ER_MSG, true);
            }

        } else if (v.getId() == R.id.texfield_update_birthday) {
            showDatePickerBottomSheet();
        } else if (v.getId() == R.id.edt_update_birthday) {
            hideSoftKeboard();
            showDatePickerBottomSheet();
        }
    }

    private void showDatePickerBottomSheet() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_date_picker, null);
        Button confirmBtn = view.findViewById(R.id.bt_confirm);
        DatePicker datePicker = view.findViewById(R.id.dp_date_picker);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, null);
        datePicker.setMaxDate(calendar.getTimeInMillis());


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        bottomSheetDialog.setContentView(view);
//        bottomSheetDialog.setCanceledOnTouchOutside(false);
//        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                String currentDate = simpleDateFormat.format(calendar.getTime());
                mBinding.edtUpdateBirthday.setText(currentDate);

                hideSoftKeboard();
                bottomSheetDialog.dismiss();

            }
        });


    }


    private void doSavingInfo() {
        hideSoftKeboard();
        if (mViewModel.getAccount() == null) {

            return;
        }
        mViewModel.getAccount().setName(mBinding.edtUpdateName.getText().toString().trim());
        mViewModel.getAccount().setBirthday(!mBinding.edtUpdateBirthday.getText().toString().trim().isEmpty() ? mBinding.edtUpdateBirthday.getText().toString().trim() : "");
        boolean state = mViewModel.checkValidInput(mBinding.edtUpdateName.getText().toString().trim());
        boolean validPhoneNum = checkValidatePhoneNumber(mBinding.edtUpdatePhone.getText().toString().trim());

        if (!state && validPhoneNum) {
            mViewModel.getAccount().setSdt(mBinding.edtUpdatePhone.getText().toString().trim());
            showProgressDialog();
            Log.i(TAG, "doSavingInfo: ChangePersonalInfo: " + state + mViewModel.getAccount().toString());
            mViewModel.checkUserToken(mViewModel.getAccount().getEmail(), mViewModel.getAccount().getApiToken());
            //mViewModel.handleSave(acount.getEmail(), acount.getApiToken(), acount.getName(), acount.getSdt(), acount.getBirthday(), acount.getPassword());

        } else {
            if (state) {
                showError(mBinding.textFieldUpdateName, "Tên người dùng trống");
            }

            if (!validPhoneNum) {
                showError(mBinding.texfieldUpdatePhone, "Số điện thoại không hợp lệ");
            }
        }
    }

    private boolean checkValidatePhoneNumber(String phoneNum) {

        if (!phoneNum.isEmpty()) {
            if (phoneNum.length() != 10) {
                return false;
            } else {
                return android.util.Patterns.PHONE.matcher(phoneNum).matches();
            }
        }
        return true;

    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            mViewModel.setState(true);
        } else if (key.equals(EnumStorage.SAVE_USER_INFO.getEnumValue())) {
            //   saveToPreference(CustomSharePreference.LOGIN_STATE, true);
            UserData userData = (UserData) data;

            mViewModel.setAccount(new User(userData.getEmail(), mViewModel.getAccount().getPassword(), userData.getApiToken(), userData.getName(), userData.getImage(), userData.getSdt(), userData.getBirthday(), userData.getVipMember()));
            Log.e(TAG, "onCheckingSuccess: ChangePersonalInfo " + mViewModel.getAccount().toString());
            mViewModel.updateAccountFromDB();
            setUiEdittext(mViewModel.getAccount());
            showSnackbar(mBinding.lnFrgSaveUser, msg, false);
        }

    }

    @Override
    public void onCallbackError(String key, String msg) {
        if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
            super.onCallbackError(key, msg);
        } else if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            mViewModel.setState(false);
            showSnackbar(mBinding.lnFrgSaveUser, msg, true);
        } else {
            showSnackbar(mBinding.lnFrgSaveUser, msg, true);

        }


    }


}
