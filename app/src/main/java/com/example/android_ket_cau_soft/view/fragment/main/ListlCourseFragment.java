package com.example.android_ket_cau_soft.view.fragment.main;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.adapter.CourseAdapterRecyclerView;
import com.example.android_ket_cau_soft.databinding.ListCourseFragmentBinding;
import com.example.android_ket_cau_soft.model.CourseData;
import com.example.android_ket_cau_soft.model.ObjectResult;
import com.example.android_ket_cau_soft.view.activity.MainActivity;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.CommonVM;

import java.util.List;

public class ListlCourseFragment extends BaseFragment<ListCourseFragmentBinding, CommonVM> implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = ListlCourseFragment.class.getName();
    private CourseAdapterRecyclerView courseAdapterRecyclerView;

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected ListCourseFragmentBinding initViewBinding(LayoutInflater inflater) {
        return ListCourseFragmentBinding.inflate(inflater);
    }

    @Override
    protected void initView() {

        mBinding.includeDetailCourse.ivBack.setOnClickListener(this);
//        mBinding.includeDetailCourse.tvBackText.setText();
        mBinding.slSwipeRefreshLayoutDetail.setOnRefreshListener(this);

        initData();
    }

    private void initData() {
        ObjectResult result = (ObjectResult) mData;
        mBinding.includeDetailCourse.tvBackText.setText(result.getName());
        courseAdapterRecyclerView = new CourseAdapterRecyclerView(mContext, (List<CourseData>) result.getListData());
        mBinding.rvDetail.setLayoutManager(new GridLayoutManager(mContext, 2));
        mBinding.rvDetail.setAdapter(courseAdapterRecyclerView);
        courseAdapterRecyclerView.getLiveData().observe(this, new Observer<CourseData>() {
            @Override
            public void onChanged(CourseData courseData) {
                mainCallBack.showFragment(DetailCourseFragment.TAG, courseData.getId()+"", true);
            }
        });

    }

    @Override
    public void onRefresh() {
        initData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mBinding.slSwipeRefreshLayoutDetail.isRefreshing()) {
                    mBinding.slSwipeRefreshLayoutDetail.setRefreshing(false);
                }

            }
        }, 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBinding.slSwipeRefreshLayoutDetail.isRefreshing()) {
            mBinding.slSwipeRefreshLayoutDetail.setRefreshing(false);
        }

    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.iv_back) {
            MainActivity mainActivity = (MainActivity) mContext;

            mainActivity.onBackPressed();
        }
    }
}
