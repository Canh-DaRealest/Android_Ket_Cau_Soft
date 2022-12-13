package com.example.android_ket_cau_soft.view.fragment.main.material;

import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.adapter.MaterialRecylcerView;
import com.example.android_ket_cau_soft.databinding.FragmentNatureDataBinding;
import com.example.android_ket_cau_soft.databinding.FragmentRawMaterialBinding;
import com.example.android_ket_cau_soft.model.ObjectResult;
import com.example.android_ket_cau_soft.model.material_search.NaturalData;
import com.example.android_ket_cau_soft.model.material_search.NaturalData2;
import com.example.android_ket_cau_soft.model.material_search.RawMaterialData;
import com.example.android_ket_cau_soft.model.material_search.RawMaterialData2;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.home.material.NaturalFragmentFrgVM;
import com.example.android_ket_cau_soft.viewmodel.home.material.RawMaterialFrgVM;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class NaturaDataFragment extends BaseFragment<FragmentNatureDataBinding, NaturalFragmentFrgVM> {
    public static final String TAG = NaturaDataFragment.class.getName();
    private List<NaturalData> rawMaterialData = new ArrayList<>();
    private List<NaturalData2> rawMaterialData2s = new ArrayList<>();

    @Override
    protected Class<NaturalFragmentFrgVM> getClassVM() {
        return NaturalFragmentFrgVM.class;
    }

    @Override
    protected FragmentNatureDataBinding initViewBinding(LayoutInflater inflater) {
        return FragmentNatureDataBinding.inflate(inflater);
    }

    @Override
    protected void initView() {



    }


    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            mViewModel.getNaturalData();
        } else

        if (key.equals(EnumStorage.GET_NATURAL_DATA.getEnumValue())) {

            rawMaterialData = (List<NaturalData>) data;
            updateData(msg, rawMaterialData);

            for (int i = 0; i < rawMaterialData.size(); i++) {
                for (int j = 0; j < rawMaterialData.get(i).getNaturalData1List().size(); j++) {
                    rawMaterialData2s.add(rawMaterialData.get(i).getNaturalData1List().get(j));
                }

            }

        }

    }

    @Override
    public void onCallbackError(String key, String msg) {
        super.onCallbackError(key, msg);
        showSnackbar(mBinding.lnNatureMain, key + ": " + msg, true);
    }

    private void updateData(String msg, List<NaturalData> lData) {
        mBinding.tvNature1.setOnClickListener(this);
        mBinding.tvNature2.setOnClickListener(this);


        mBinding.tvNature1.setText(lData.get(0).getAddress_1());
        mBinding.tvNature2.setText(lData.get(0).getNaturalData1List().get(0).getAddress_2());
        mBinding.tvNature3.setText(lData.get(0).getNaturalData1List().get(0).getData3().toString());

    }


    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.iv_back) {
            backToPreviousFragment();

        } else if (v.getId() == R.id.tv_nature1) {

            showBottomSheet();

        } else if (v.getId() == R.id.tv_nature2) {

            showBottomSheet2();


        }
    }

    private void showBottomSheet2() {

        MaterialRecylcerView adapter = new MaterialRecylcerView(mContext, rawMaterialData2s);
        adapter.setDataType(6);

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
                mBinding.tvNature2.setText(objectResult.getName());
                mBinding.tvNature3.setText(objectResult.getData().toString());
                bottomSheetDialog.dismiss();
            }
        });

    }

    private void showBottomSheet() {
        MaterialRecylcerView adapter = new MaterialRecylcerView(mContext, rawMaterialData);
        adapter.setDataType(5);

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
                mBinding.tvNature1.setText(objectResult.getName());

                mBinding.tvNature2.setText("Chọn");
                mBinding.tvNature3.setText("");

                rawMaterialData2s = (List<NaturalData2>) objectResult.getListData();
                bottomSheetDialog.dismiss();
            }
        });


    }

}
