package com.example.android_ket_cau_soft.view.fragment.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.adapter.ItemLessonPathAdapter;
import com.example.android_ket_cau_soft.databinding.DetailCourseFragmentBinding;
import com.example.android_ket_cau_soft.model.IntentResult;
import com.example.android_ket_cau_soft.model.lesson.ItemLesson;
import com.example.android_ket_cau_soft.model.lesson.ItemLessonPath;
import com.example.android_ket_cau_soft.model.lesson.LessonData;
import com.example.android_ket_cau_soft.view.activity.MainActivity;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.home.DetailCourseVM;

import java.util.List;

public class DetailCourseFragment extends BaseFragment<DetailCourseFragmentBinding, DetailCourseVM> {
    public static final String TAG = DetailCourseFragment.class.getName();


    @Override
    protected Class<DetailCourseVM> getClassVM() {
        return DetailCourseVM.class;
    }

    @Override
    protected DetailCourseFragmentBinding initViewBinding(LayoutInflater inflater) {
        return DetailCourseFragmentBinding.inflate(inflater);
    }

    @Override
    protected void initView() {

        String courseId = (String) mData;
        mBinding.includeDetailCourse.ivBack.setOnClickListener(this);
        mViewModel.updateCourseContent(courseId);


    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.DETAILCOURSE_REQUEST.getEnumValue())) {

            LessonData lessonData = (LessonData) data;
            mViewModel.setLessonData(lessonData);

            mBinding.includeDetailCourse.tvBackText.setText(mViewModel.getLessonData().getName());
            mBinding.tvMentorName.setText(mViewModel.getLessonData().getMentor());
            mBinding.tvLessonNumber.setText(String.format("%d bài giảng", lessonData.getLessonNum()));
            mBinding.tvStudentNumber.setText(String.format("%d học viên", lessonData.getRated()));
            int timeInSecond = mViewModel.getLessonData().getTime();
            String formatTime = formatTime(timeInSecond);
            mBinding.tvTotalTime.setText(String.format("thời gian: %s", formatTime));

            addLesson(mViewModel.getLessonData());

        }
    }

    private String formatTime(int timeInSecond) {
        int hour = timeInSecond / 3600;
        int minute = (timeInSecond % 3600) / 60;
        int second = timeInSecond % 60;
        String formatedTime = String.format("%02d:%02d:%02d", hour, minute, second);
        return formatedTime;
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.iv_back) {
            MainActivity homeActivity = (MainActivity) mContext;

            homeActivity.onBackPressed();
        }
    }

    private void addLesson(LessonData lessonData) {
        List<ItemLessonPath> itemLessonPathList = lessonData.getLessonList();


        if (itemLessonPathList != null) {

            Log.i(TAG, "addLesson: " + itemLessonPathList.size());
            ItemLessonPathAdapter itemLessonPathAdapter = new ItemLessonPathAdapter(mContext, itemLessonPathList);
            mBinding.rvLessonPath.setAdapter(itemLessonPathAdapter);
            mBinding.rvLessonPath.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
            itemLessonPathAdapter.getItemPathLiveData().observe(this, new Observer<ItemLesson>() {
                @Override
                public void onChanged(ItemLesson itemLesson) {
                    doClickItemLesson(itemLesson);
                }
            });

        }
    }

    private void doClickItemLesson(ItemLesson itemLesson) {
        mainCallBack.showFragment(DetailLessonFragment.TAG, new IntentResult(mViewModel.getLessonData().getName(),itemLesson), true);

    }


}
