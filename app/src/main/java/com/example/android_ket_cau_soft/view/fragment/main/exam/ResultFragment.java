package com.example.android_ket_cau_soft.view.fragment.main.exam;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.adapter.FalseAnswerAdapter;
import com.example.android_ket_cau_soft.databinding.FragmentResultBinding;
import com.example.android_ket_cau_soft.model.exam.ExamResult;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.home.exam.ResultFrgVM;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultFragment extends BaseFragment<FragmentResultBinding, ResultFrgVM> {
    public static final String TAG = ResultFragment.class.getName();

    @Override
    protected Class<ResultFrgVM> getClassVM() {
        return ResultFrgVM.class;
    }

    @Override
    protected FragmentResultBinding initViewBinding(LayoutInflater inflater) {
        return FragmentResultBinding.inflate(inflater);
    }

    @Override
    protected void initView() {


        mBinding.btTestAgain.setOnClickListener(this);

        ExamResult examResult = (ExamResult) mData;
        mBinding.btTestAgain.setOnClickListener(this);
        mViewModel.setChooseAnswerList(examResult.getChooseAnswerList());
        mViewModel.setQuestionDataList(examResult.getQuestionDataList());


        if (mViewModel.getQuestionDataList() == null) {
            Log.e(TAG, "initView: nulll");
        } else {
            Log.e(TAG, "initView: notnulll");
            updateMpPieChart();
        }


    }

    private void updateMpPieChart() {

        mViewModel.updateScore();

        mBinding.tvTotalTrueAnswer.setText(mViewModel.getScore() + "/" + mViewModel.getTotalScore());
        mBinding.tvResult.setText(mViewModel.getScore() < mViewModel.getTotalScore() ? "Không đạt" : "Đạt");
        mBinding.tvResult.setTextColor(mBinding.tvResult.getText().equals("Không đạt") ? mContext.getColor(R.color.red) : mContext.getColor(R.color.blue));

        FalseAnswerAdapter falseAnswerAdapter = new FalseAnswerAdapter(mContext, mViewModel.getFalseQuestionList());

        mBinding.rvFalseAnswer.setAdapter(falseAnswerAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);

        mBinding.rvFalseAnswer.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mBinding.rvFalseAnswer.addItemDecoration(itemDecoration);

//list color để dat màu cho biểu đồ
        List<Integer> listColor = new ArrayList<>();
        listColor.add(Color.YELLOW);
        listColor.add(Color.GREEN);


        List<PieEntry> pieEntryList = new ArrayList<>();
        pieEntryList.add(new PieEntry(mViewModel.getScore() / 4, "Số câu đúng"));
        pieEntryList.add(new PieEntry(mViewModel.getTotalScore() / 4 - mViewModel.getScore() / 4, "Số câu sai"));


        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "");
        pieDataSet.setColors(listColor);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(14);

        mBinding.mpPieChart.setData(pieData);
        mBinding.mpPieChart.setEntryLabelColor(Color.BLACK); //màu của tỉ lệ %
        mBinding.mpPieChart.setEntryLabelTextSize(12); //kich thước của tỉ lệ  %
        mBinding.mpPieChart.setDescription(null);  //tiêu đề biểu đồ

        mBinding.mpPieChart.invalidate();

    }


    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.bt_test_again) {
            backToPreviousFragment();
        }
    }


}
