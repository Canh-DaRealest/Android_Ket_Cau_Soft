package com.example.android_ket_cau_soft.view.fragment.main.material;

import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.adapter.MaterialRecylcerView;
import com.example.android_ket_cau_soft.databinding.FragmentLiveLoadBinding;
import com.example.android_ket_cau_soft.model.ObjectResult;
import com.example.android_ket_cau_soft.model.material_search.LiveLoadData;
import com.example.android_ket_cau_soft.model.material_search.LiveLoadData2;
import com.example.android_ket_cau_soft.model.material_search.LiveLoadData3;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.home.material.LiveLoadFrgVM;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class LiveLoadFragment extends BaseFragment<FragmentLiveLoadBinding, LiveLoadFrgVM> {
    public static final String TAG = LiveLoadFragment.class.getName();
    private List<LiveLoadData> liveLoadDataList = new ArrayList<>();
    private List<LiveLoadData2> liveLoadData2List = new ArrayList<>();

    @Override
    protected Class<LiveLoadFrgVM> getClassVM() {
        return LiveLoadFrgVM.class;
    }

    @Override
    protected FragmentLiveLoadBinding initViewBinding(LayoutInflater inflater) {
        return FragmentLiveLoadBinding.inflate(inflater);
    }

    @Override
    protected void initView() {


    }


    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            mViewModel.getLiveLoadData();
        } else if (key.equals(EnumStorage.GET_LIVE_LOAD.getEnumValue())) {

            liveLoadDataList = (List<LiveLoadData>) data;
            updateData(msg, liveLoadDataList);

            for (int i = 0; i < liveLoadDataList.size(); i++) {
                for (int j = 0; j < liveLoadDataList.get(i).getLiveLoadData2List().size(); j++) {
                    liveLoadData2List.add(liveLoadDataList.get(i).getLiveLoadData2List().get(j));
                }

            }

        }

    }

    @Override
    public void onCallbackError(String key, String msg) {
        super.onCallbackError(key, msg);
        showSnackbar(mBinding.lnLiveloadMain, msg, true);
    }

    private void updateData(String msg, List<LiveLoadData> lData) {
        mBinding.tvLiveload1.setOnClickListener(this);
        mBinding.tvLiveload2.setOnClickListener(this);


        mBinding.tvLiveload1.setText(lData.get(0).getAddress_1());
        mBinding.tvLiveload2.setText(lData.get(0).getLiveLoadData2List().get(0).getAddress_2());
        mBinding.tvLiveload3.setText(lData.get(0).getLiveLoadData2List().get(0).getData_2().toString());

    }


    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.iv_back) {
            backToPreviousFragment();

        } else if (v.getId() == R.id.tv_liveload1) {

            showBottomSheet();

        } else if (v.getId() == R.id.tv_liveload2) {

            showBottomSheet2();


        }
    }

    private void showBottomSheet2() {

        MaterialRecylcerView adapter = new MaterialRecylcerView(mContext, liveLoadData2List);
        adapter.setDataType(2);

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_bs_live_load, null);
        RecyclerView recyclerView = view.findViewById(R.id.rv_material_item);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        adapter.getListMutableLiveData2().observe(this, new Observer<ObjectResult>() {
            @Override
            public void onChanged(ObjectResult objectResult) {
                mBinding.tvLiveload2.setText(objectResult.getName());
                mBinding.tvLiveload3.setText(objectResult.getData().toString());
                bottomSheetDialog.dismiss();
            }
        });

    }

    private void showBottomSheet() {
        MaterialRecylcerView adapter = new MaterialRecylcerView(mContext, liveLoadDataList);
        adapter.setDataType(1);

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_bs_live_load, null);
        RecyclerView recyclerView = view.findViewById(R.id.rv_material_item);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        adapter.getListMutableLiveData2().observe(this, new Observer<ObjectResult>() {
            @Override
            public void onChanged(ObjectResult objectResult) {
                mBinding.tvLiveload1.setText(objectResult.getName());

                mBinding.tvLiveload2.setText("Chọn");
                mBinding.tvLiveload3.setText("");

                liveLoadData2List = (List<LiveLoadData2>) objectResult.getListData();
                bottomSheetDialog.dismiss();
            }
        });


    }

}
