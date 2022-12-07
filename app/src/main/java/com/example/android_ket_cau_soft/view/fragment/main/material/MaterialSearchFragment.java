package com.example.android_ket_cau_soft.view.fragment.main.material;

import android.view.LayoutInflater;
import android.view.View;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.adapter.CourseAdapterRecyclerView;
import com.example.android_ket_cau_soft.databinding.FragmentExaminationBinding;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.DetailLessonVM;

public class MaterialSearchFragment extends BaseFragment<FragmentExaminationBinding, DetailLessonVM> {
    public static final String TAG = MaterialSearchFragment.class.getName();
    private CourseAdapterRecyclerView courseAdapterRecyclerView;

    @Override
    protected Class<DetailLessonVM> getClassVM() {
        return DetailLessonVM.class;
    }

    @Override
    protected FragmentExaminationBinding initViewBinding(LayoutInflater inflater) {
        return FragmentExaminationBinding.inflate(inflater);
    }

    @Override
    protected void initView() {




    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);

    }





    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.iv_back) {
            backToPreviousFragment();
        }
    }


}
