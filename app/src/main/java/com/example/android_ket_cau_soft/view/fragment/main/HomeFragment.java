package com.example.android_ket_cau_soft.view.fragment.main;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.adapter.CourseAdapter;
import com.example.android_ket_cau_soft.adapter.HotNewsAdapter;
import com.example.android_ket_cau_soft.callback.OnUpdateCountCallback;
import com.example.android_ket_cau_soft.databinding.FragmentHomeBinding;
import com.example.android_ket_cau_soft.model.CourseData;
import com.example.android_ket_cau_soft.model.NewsData;
import com.example.android_ket_cau_soft.model.ObjectResult;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.view.fragment.main.exam.ExaminationFragment;
import com.example.android_ket_cau_soft.viewmodel.home.HomeFragmentVM;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentVM> implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = HomeFragment.class.getName();

    private final Handler handler = new Handler();
    private OnUpdateCountCallback onUpdateCountCallback;


    public void setOnUpdateCountCallback(OnUpdateCountCallback onUpdateCountCallback) {
        this.onUpdateCountCallback = onUpdateCountCallback;
    }

    private Runnable getRunnable(ViewPager2 view, List<?> list) {

        return new Runnable() {
            @Override
            public void run() {
                //mBinding.vpNews.vpViewpager2.getCurrentItem()    mViewModel.getNewsDataList()
                if (view.getCurrentItem() >= list.size() - 1) {
                    view.setCurrentItem(0);
                } else if (view.getCurrentItem() < 0) {
                    view.setCurrentItem(list.size() - 1);
                } else {
                    view.setCurrentItem(view.getCurrentItem() + 1);
                }
            }
        };
    }

    @Override
    protected Class<HomeFragmentVM> getClassVM() {
        return HomeFragmentVM.class;
    }

    @Override
    protected FragmentHomeBinding initViewBinding(LayoutInflater inflater) {
        return FragmentHomeBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        mBinding.slHomeSwipeRefreshLayout.setOnRefreshListener(this);
        mBinding.includeTest.ivLiveLoad.setOnClickListener(this);
        mBinding.includeTest.ivCchnExam.setOnClickListener(this);
        mBinding.includeTest.ivRawMaterial.setOnClickListener(this);
        mBinding.includeTest.ivNaturalNumber.setOnClickListener(this);
        initData();


    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.iv_cchn_exam) {
            doClickExaminationCCHN();

        }else if (v.getId()==R.id.iv_live_load){


        }else if (v.getId()==R.id.iv_raw_material){

        }else  if (v.getId()==R.id.iv_natural_number){

        }
    }

    private void doClickExaminationCCHN() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cchn_examination, null);
        TextView tvFullExam = view.findViewById(R.id.tv_full_exam);
        TextView tvHalftExam = view.findViewById(R.id.tv_halft_exam);
        tvFullExam.setTag(1);
        tvHalftExam.setTag(0);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        tvFullExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExamFragment((int)v.getTag());
                Log.i(TAG, "onClick: full "+v.getTag());
                bottomSheetDialog.dismiss();
            }
        });
        tvHalftExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExamFragment((int)v.getTag());
                Log.i(TAG, "onClick: halft "+v.getTag());
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void showExamFragment(int tag) {
        onParentFrgCallback.showFragmentFromMenu(ExaminationFragment.TAG, tag, true);
    }

    private void initData() {


        mViewModel.updateNewsDataList();
        mViewModel.updateHotCoursesList();
        mViewModel.updateNewCoursesList();

        mViewModel.updateAccountFromDB();
        try {
            mViewModel.checkToken(mViewModel.getAccount().getEmail(), mViewModel.getAccount().getApiToken());
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.e(TAG, "initView: checkToken at mainFrg " + e.getMessage());
        }
    }

    private void autoSlide(ViewPager2 vp2Viewpager2, Runnable runnable) {

        handler.postDelayed(runnable, 5000);

        vp2Viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 5000);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);

        if (key.equals(EnumStorage.HOTNEWS_REQUEST.getEnumValue())) {

            mBinding.includeItemType1.tvType.setVisibility(View.GONE);
            mBinding.includeItemType1.ivIconType.setVisibility(View.GONE);
            mBinding.includeItemType1.tvSeeMore.setVisibility(View.GONE);

            NewsData[] mData = (NewsData[]) data;
            mViewModel.setNewsDataList(mData);
            HotNewsAdapter adapter = new HotNewsAdapter(mContext, mViewModel.getNewsDataList());
            adapter.setType(HotNewsAdapter.VIEWPAGER_TYPE);
            mBinding.vpNews.vp2Viewpager2.setAdapter(adapter);

            adapter.getLiveData().observe(this, new Observer<Object>() {
                @Override
                public void onChanged(Object o) {
                    NewsData data1 = (NewsData) o;
                    showWebFragment(data1.getLink());
                }


            });

            autoSlide(mBinding.vpNews.vp2Viewpager2, getRunnable(mBinding.vpNews.vp2Viewpager2, mViewModel.getNewsDataList()));
        } else if (key.equals(EnumStorage.GET_NOTIFICATION.getEnumValue())) {

            onUpdateCountCallback.updateCount(Integer.parseInt(msg));
            Log.i(TAG, "onCallbackSuccess: getnotice " + Integer.parseInt(msg));

        } else {


            List<CourseData> mData = (List<CourseData>) data;

            if (key.equals(EnumStorage.HOTCOURSE_REQUEST.getEnumValue())) {
                mBinding.includeItemType2.tvType.setText(msg);
                mBinding.includeItemType2.tvSeeMore.setVisibility(mData.size() > 0 ? View.VISIBLE : View.GONE);

                mViewModel.setHotCourseList(mData);

                CourseAdapter hotCourseAdapter = new CourseAdapter(mContext, mViewModel.getHotCourseData());

                clickBttnSeemore(mBinding.includeItemType2.tvSeeMore, new ObjectResult(msg, mViewModel.getHotCourseData()));

                hotCourseAdapter.getLiveData().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        showDetailCourseInfor(s);
                    }
                });

                mBinding.vp2HotCourses.vp2Viewpager2.setAdapter(hotCourseAdapter);
                mBinding.inclueIndicator1.circleIndiCator.setViewPager(mBinding.vp2HotCourses.vp2Viewpager2);
                autoSlide(mBinding.vp2HotCourses.vp2Viewpager2, getRunnable(mBinding.vp2HotCourses.vp2Viewpager2, mViewModel.getHotCourseData()));

            } else if (key.equals(EnumStorage.NEWCOURSE_REQUEST.getEnumValue())) {

                mBinding.includeItemType3.tvType.setText(msg);
                mBinding.includeItemType3.tvSeeMore.setVisibility(mData.size() > 0 ? View.VISIBLE : View.GONE);
                mViewModel.setNewCourseList(mData);

                CourseAdapter newCourseAdapter = new CourseAdapter(mContext, mViewModel.getNewCourseData());
                clickBttnSeemore(mBinding.includeItemType3.tvSeeMore, new ObjectResult(msg, mViewModel.getNewCourseData()));
                newCourseAdapter.getLiveData().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        showDetailCourseInfor(s);
                    }
                });

                mBinding.vp2NewCourses.vp2Viewpager2.setAdapter(newCourseAdapter);
                mBinding.inclueIndicator2.circleIndiCator.setViewPager(mBinding.vp2NewCourses.vp2Viewpager2);
                autoSlide(mBinding.vp2NewCourses.vp2Viewpager2, getRunnable(mBinding.vp2NewCourses.vp2Viewpager2, mViewModel.getNewCourseData()));
            }


        }

    }

    private void showDetailCourseInfor(String courseId) {

        onParentFrgCallback.showFragmentFromMenu(DetailCourseFragment.TAG, courseId, true);

    }

    @Override
    public void onCallbackError(String key, String msg) {
        super.onCallbackError(key, msg);
    }

    private void clickBttnSeemore(TextView tvSeeMore, ObjectResult objectResult) {
        tvSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListCourseFrg(objectResult);
            }
        });
    }


    private void showListCourseFrg(ObjectResult listData) {

        onParentFrgCallback.showFragmentFromMenu(ListlCourseFragment.TAG, listData, true);

    }

    private void showWebFragment(String url) {


        onParentFrgCallback.showFragmentFromMenu(WebViewFragment.TAG, url, true);
    }

    @Override
    public void onRefresh() {
        initData();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mBinding.slHomeSwipeRefreshLayout.isRefreshing())
                    mBinding.slHomeSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBinding.slHomeSwipeRefreshLayout.isRefreshing()) {
            mBinding.slHomeSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
