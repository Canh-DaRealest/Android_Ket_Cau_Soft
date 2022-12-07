package com.example.android_ket_cau_soft.view.fragment.main;

import android.view.LayoutInflater;

import com.example.android_ket_cau_soft.databinding.ItemNewChildBinding;
import com.example.android_ket_cau_soft.model.NewsData;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.CommonVM;


public class HotNewsItemFragment extends BaseFragment<ItemNewChildBinding, CommonVM> {
    private NewsData data;

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    public HotNewsItemFragment(NewsData data) {
        this.data = data;
    }

    public HotNewsItemFragment() {
    }

    @Override
    protected ItemNewChildBinding initViewBinding(LayoutInflater inflater) {
        return ItemNewChildBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        updateUI();
    }

    private void updateUI() {

        mBinding.tvNewsName.setText(data.getName());
        String time = getTime(data.getTime());
        String gio = "";
        for (int i = 0; i < time.length(); i++) {
            char character = time.charAt(i);
            String giomin = "";


            if (character != '-') {

                giomin += character;
            }
            if (character == '-') {
                giomin = "/" + giomin;
                gio += giomin;
            }
            giomin = "";

        }

        mBinding.tvNewTime.setText(gio);

    }

    private String getTime(String time) {

        String gio = "";
        for (int i = 0; i < time.length(); i++) {
            char character = time.charAt(i);
            if (character == 'T') {
                return gio;
            } else {
                gio += character;
            }
        }
        return null;
    }

}
