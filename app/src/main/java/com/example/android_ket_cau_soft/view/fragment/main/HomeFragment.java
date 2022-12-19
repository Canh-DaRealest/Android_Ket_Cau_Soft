package com.example.android_ket_cau_soft.view.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.adapter.CourseAdapter;
import com.example.android_ket_cau_soft.adapter.HotNewsAdapter;
import com.example.android_ket_cau_soft.callback.OnUpdateCountCallback;
import com.example.android_ket_cau_soft.databinding.FragmentHomeBinding;
import com.example.android_ket_cau_soft.model.CourseData;
import com.example.android_ket_cau_soft.model.NewsData;
import com.example.android_ket_cau_soft.model.ObjectResult;
import com.example.android_ket_cau_soft.model.material_search.LiveLoadData;
import com.example.android_ket_cau_soft.model.material_search.NaturalData;
import com.example.android_ket_cau_soft.model.material_search.RawMaterialData;
import com.example.android_ket_cau_soft.sharepreference.CustomSharePreference;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.view.fragment.login.LoginFragment;
import com.example.android_ket_cau_soft.view.fragment.main.exam.ExaminationFragment;
import com.example.android_ket_cau_soft.view.fragment.main.material.LiveLoadFragment;
import com.example.android_ket_cau_soft.view.fragment.main.material.NaturaDataFragment;
import com.example.android_ket_cau_soft.view.fragment.main.material.RawMaterialFragment;
import com.example.android_ket_cau_soft.viewmodel.home.HomeFragmentVM;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentVM> implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = HomeFragment.class.getName();


    private OnUpdateCountCallback onUpdateCountCallback;


    public void setOnUpdateCountCallback(OnUpdateCountCallback onUpdateCountCallback) {
        this.onUpdateCountCallback = onUpdateCountCallback;
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
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.iv_cchn_exam) {
            doClickExaminationCCHN();

        } else if (v.getId() == R.id.iv_live_load) {

            showLiveLoadFrg();

        } else if (v.getId() == R.id.iv_raw_material) {
            showRawMaterialFragment();

        } else if (v.getId() == R.id.iv_natural_number) {
            showNaturalDataFragment();
        }
    }

    private void doClickExaminationCCHN() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cchn_examination, null);
        TextView tvFullExam = view.findViewById(R.id.tv_full_exam);
        TextView tvHalfExam = view.findViewById(R.id.tv_halft_exam);
        tvFullExam.setTag(1);
        tvHalfExam.setTag(0);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        tvFullExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExamFragment((int) v.getTag());
                Log.i(TAG, "onClick: full " + v.getTag());
                bottomSheetDialog.dismiss();
            }
        });
        tvHalfExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExamFragment((int) v.getTag());
                Log.i(TAG, "onClick: halft " + v.getTag());
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void showExamFragment(int tag) {
        onParentFrgCallback.showFragmentFromMenu(ExaminationFragment.TAG, tag, true);
    }

    private void initData() {
        if (HomeFragmentVM.isNull) {
            Log.e(TAG, "initData: isnull"+HomeFragmentVM.isNull );
            onUpdateCountCallback.updateCount(HomeFragmentVM.unReadNotify);
            updateHotNewsAdapter();
            updateHotCourseAdapter(HomeFragmentVM.hotCourseTitle);
            updateNewCourseAdapter(HomeFragmentVM.newCourseTitle);
        } else {

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

    }


    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            initData();

        } else if (key.equals(EnumStorage.HOTNEWS_REQUEST.getEnumValue())) {

         updateHotNewsAdapter();

        } else if (key.equals(EnumStorage.GET_NOTIFICATION.getEnumValue())) {

            onUpdateCountCallback.updateCount(HomeFragmentVM.unReadNotify);

        } else {


            if (key.equals(EnumStorage.HOTCOURSE_REQUEST.getEnumValue())) {
              updateHotCourseAdapter(msg);


            } else if (key.equals(EnumStorage.NEWCOURSE_REQUEST.getEnumValue())) {

            updateNewCourseAdapter(msg);

            }


        }

    }

    private void updateNewCourseAdapter(String msg) {
        mBinding.includeItemType3.tvType.setText(msg);
        mBinding.includeItemType3.tvSeeMore.setVisibility(mViewModel.getNewCourseData()
                .size() > 0 ? View.VISIBLE : View.GONE);


        CourseAdapter newCourseAdapter = new CourseAdapter(mContext, mViewModel.getNewCourseData());
        newCourseAdapter.setRvType(CourseAdapter.TYPE_AUTO_SLIDE);
        clickBttnSeemore(mBinding.includeItemType3.tvSeeMore, new ObjectResult(msg, mViewModel.getNewCourseData()));
        newCourseAdapter.getLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showDetailCourseInfor(s);
            }
        });

        mBinding.vp2NewCourses.setAdapter(newCourseAdapter);


        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();


        if (mBinding.vp2NewCourses.getOnFlingListener() == null) {
            LinearLayoutManager linearLayoutMng = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            mBinding.vp2NewCourses.setLayoutManager(linearLayoutMng);

            linearSnapHelper.attachToRecyclerView(mBinding.vp2NewCourses);
            autoSlide(mBinding.vp2NewCourses, newCourseAdapter, linearLayoutMng, 4500);

            mBinding.inclueIndicator2.circleIndiCator.attachToRecyclerView(mBinding.vp2HotCourses, linearSnapHelper);

// optional
            newCourseAdapter.registerAdapterDataObserver(mBinding.inclueIndicator2.circleIndiCator.getAdapterDataObserver());

        }
    }

    private void updateHotCourseAdapter(String msg) {
        mBinding.includeItemType2.tvType.setText(msg);
        mBinding.includeItemType2.tvSeeMore.setVisibility(mViewModel.getHotCourseData().size() > 0 ? View.VISIBLE : View.GONE);


        CourseAdapter hotCourseAdapter = new CourseAdapter(mContext, mViewModel.getHotCourseData());
        hotCourseAdapter.setRvType(CourseAdapter.TYPE_AUTO_SLIDE);
        clickBttnSeemore(mBinding.includeItemType2.tvSeeMore, new ObjectResult(msg, mViewModel.getHotCourseData()));

        hotCourseAdapter.getLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showDetailCourseInfor(s);
            }
        });

        mBinding.vp2HotCourses.setAdapter(hotCourseAdapter);

        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();

        if (mBinding.vp2HotCourses.getOnFlingListener() == null) {
            LinearLayoutManager linearLayoutMng = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            mBinding.vp2HotCourses.setLayoutManager(linearLayoutMng);

            linearSnapHelper.attachToRecyclerView(mBinding.vp2HotCourses);
            autoSlide(mBinding.vp2NewCourses, hotCourseAdapter, linearLayoutMng, 4000);
            mBinding.inclueIndicator1.circleIndiCator.attachToRecyclerView(mBinding.vp2HotCourses, linearSnapHelper);

// optional
            hotCourseAdapter.registerAdapterDataObserver(mBinding.inclueIndicator1.circleIndiCator.getAdapterDataObserver());

        }
    }

    private void updateHotNewsAdapter() {
        HotNewsAdapter adapter = new HotNewsAdapter(mContext, mViewModel.getNewsDataList());
        adapter.setType(HotNewsAdapter.HOME_TYPE);
        mBinding.vpNews.setAdapter(adapter);

        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        if (mBinding.vpNews.getOnFlingListener() == null) {
            LinearLayoutManager linearLayoutMng = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);

            mBinding.vpNews.setLayoutManager(linearLayoutMng);
            linearSnapHelper.attachToRecyclerView(mBinding.vpNews);
            autoSlide(mBinding.vp2NewCourses, adapter, linearLayoutMng, 3000);

        }


        adapter.getLiveData().observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                NewsData data1 = (NewsData) o;
                showWebFragment(data1.getLink());
            }


        });

    }

    private void showRawMaterialFragment() {
        onParentFrgCallback.showFragmentFromMenu(RawMaterialFragment.TAG, null, true);
    }

    private void showNaturalDataFragment() {
        onParentFrgCallback.showFragmentFromMenu(NaturaDataFragment.TAG, null, true);
    }

    private void showLiveLoadFrg() {

        onParentFrgCallback.showFragmentFromMenu(LiveLoadFragment.TAG, null, true);

    }


    private void showDetailCourseInfor(String courseId) {

        onParentFrgCallback.showFragmentFromMenu(DetailCourseFragment.TAG, courseId, true);

    }

    @Override
    public void onCallbackError(String key, String msg) {
        if (isAdded()) {
            showSnackbar(requireActivity().findViewById(R.id.sl_home_swipeRefreshLayout), msg, true);
        }
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
        checkNetworkConnection();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mBinding.slHomeSwipeRefreshLayout.isRefreshing()) {
                    Toast.makeText(mContext, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                    mBinding.slHomeSwipeRefreshLayout.setRefreshing(false);
                }

            }
        }, 1000);

    }

    private void autoSlide(RecyclerView recyclerView, RecyclerView.Adapter adapter, LinearLayoutManager linearLayoutManager, int delayTime) {


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() < adapter.getItemCount() - 1) {
                    linearLayoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(),
                            linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                } else if (linearLayoutManager.findLastCompletelyVisibleItemPosition() >= adapter.getItemCount() - 1) {
                    linearLayoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(),
                            0);
                }
            }
        }, 0, delayTime);

    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop: homefragment is stopped");
        super.onStop();
        if (mBinding.slHomeSwipeRefreshLayout.isRefreshing()) {
            mBinding.slHomeSwipeRefreshLayout.setRefreshing(false);
        }
        if (mViewModel.getCall() != null) {
            mViewModel.getCall().cancel();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume:  homefragment is resume");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: homefragment is attacked");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: homefragment is destroyed");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate: homefragment is created");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: homefragment is creatview");
        Log.i(TAG, "onViewCreated: isnull before createview" + mViewModel.isNull);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated: homefragment is viewcreated");
        Log.i(TAG, "onViewCreated: isnull after " + mViewModel.isNull);
        super.onViewCreated(view, savedInstanceState);
    }
}
