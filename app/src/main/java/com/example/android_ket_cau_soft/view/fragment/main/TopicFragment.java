package com.example.android_ket_cau_soft.view.fragment.main;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.adapter.CourseTopicAdapter;
import com.example.android_ket_cau_soft.databinding.FragmentLessonBinding;
import com.example.android_ket_cau_soft.model.CourseData;
import com.example.android_ket_cau_soft.model.ItemTopic;
import com.example.android_ket_cau_soft.model.ObjectResult;
import com.example.android_ket_cau_soft.sharepreference.CustomSharePreference;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.view.fragment.login.LoginFragment;
import com.example.android_ket_cau_soft.viewmodel.TopicVM;

import java.util.List;

public class TopicFragment extends BaseFragment<FragmentLessonBinding, TopicVM> implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = TopicFragment.class.getName();

    @Override
    protected Class<TopicVM> getClassVM() {
        return TopicVM.class;
    }

    @Override
    protected FragmentLessonBinding initViewBinding(LayoutInflater inflater) {
        return FragmentLessonBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        mBinding.slCourseTopic.setOnRefreshListener(this);
        getTopic();
        mBinding.tvMyCourse.setOnClickListener(this);
    }

    private void getTopic() {
        mViewModel.getTopic();
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);


        if (v.getId() == R.id.tv_my_course) {

            doClickBttMyCourse();

        }
    }

    private void doClickBttMyCourse() {

        mViewModel.updateAccountFromDB();
        mViewModel.checkToken(mViewModel.getAccount().getEmail(), mViewModel.getAccount().getApiToken());

    }

    private void showMyCourse() {
        mViewModel.getMyCourse();
    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.GET_TOPIC.getEnumValue())) {
            List<ItemTopic> topicList = (List<ItemTopic>) data;
            CourseTopicAdapter courseTopicAdapter = new CourseTopicAdapter(mContext, topicList);

            mBinding.rvCourseTopic.setAdapter(courseTopicAdapter);
            mBinding.rvCourseTopic.setLayoutManager(new GridLayoutManager(mContext, 2));

            courseTopicAdapter.getItData().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer id) {
                    mViewModel.getCourseByTopic(id);
                }
            });
        } else if (key.equals(EnumStorage.GET_DETAIL_BY_TOPIC.getEnumValue())) {
            List<CourseData> topicList = (List<CourseData>) data;
            showListCourseFrg(new ObjectResult(msg, topicList));

        } else if (key.equals(EnumStorage.GET_MY_COURSE.getEnumValue())) {

            List<CourseData> topicList = (List<CourseData>) data;

            if (topicList.size() <= 0) {
                showSnackbar(mBinding.slCourseTopic, "Bạn chưa có khóa học nào", true);
                return;
            }
            showListCourseFrg(new ObjectResult(msg, topicList));
        } else if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
            showMyCourse();
        }
    }

    private void showListCourseFrg(ObjectResult objectResult) {
        onParentFrgCallback.showFragmentFromMenu(ListlCourseFragment.TAG, objectResult, true);
    }

    @Override
    public void onCallbackError(String key, String msg) {
        super.onCallbackError(key, msg);
        if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
            getNoticeDialog(mContext).setUpDialog(" Thông báo", "Tài khoản đã được đăng nhập ở nơi khác, vui lòng đăng nhập lại", "Ok", null, true, new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dismissNoticeDialog();

                    onParentFrgCallback.showFragmentFromMenu(LoginFragment.TAG, null, false);
                    CustomSharePreference.getInstance().saveBooleanValue(CustomSharePreference.LOGIN_STATE, false);
                }
            });
            showNoticeDialog();
        } else {
            showSnackbar(mBinding.slCourseTopic, msg, true);
        }

    }


    @Override
    public void onRefresh() {
        getTopic();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mBinding.slCourseTopic.isRefreshing()) {
                    Toast.makeText(mContext, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                    mBinding.slCourseTopic.setRefreshing(false);
                }
            }
        }, 1000);
    }
}
