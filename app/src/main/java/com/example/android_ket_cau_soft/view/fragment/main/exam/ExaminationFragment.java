package com.example.android_ket_cau_soft.view.fragment.main.exam;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.databinding.FragmentExaminationBinding;
import com.example.android_ket_cau_soft.model.exam.ExamResult;
import com.example.android_ket_cau_soft.model.exam.QuestionData;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.home.exam.ExaminationVM;

import java.util.List;

public class ExaminationFragment extends BaseFragment<FragmentExaminationBinding, ExaminationVM> {
    public static final String TAG = ExaminationFragment.class.getName();

    @Override
    protected Class<ExaminationVM> getClassVM() {
        return ExaminationVM.class;
    }

    @Override
    protected FragmentExaminationBinding initViewBinding(LayoutInflater inflater) {
        return FragmentExaminationBinding.inflate(inflater);
    }

    @Override
    protected void initView() {

        updateRadiobuttonlist();
        mBinding.btSubmit.setOnClickListener(this);

        mViewModel.setExamType((int) mData);
        Log.i(TAG, "initView: examtype" + (int) mData);


    }

    private void updateRadiobuttonlist() {
        mBinding.rbAnswerA.setTag(1);
        mBinding.rbAnswerB.setTag(2);
        mBinding.rbAnswerC.setTag(3);
        mBinding.rbAnswerD.setTag(4);

        mViewModel.setRadioButtonList(mBinding.rbAnswerA);
        mViewModel.setRadioButtonList(mBinding.rbAnswerB);
        mViewModel.setRadioButtonList(mBinding.rbAnswerC);
        mViewModel.setRadioButtonList(mBinding.rbAnswerD);

    }


    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            mViewModel.getExamQuestion(mViewModel.getExamType());
        } else if (key.equals(EnumStorage.GET_EXAM_QUESTION.getEnumValue())) {
            mViewModel.setListQuestionData((List<QuestionData>) data);
            showExam();
        }
    }

    private void showExam() {

        if (mViewModel.getCurrentQuestion() >= mViewModel.getQuestionDataList().size() - 1) {
            mBinding.btSubmit.setText("Nộp bài");
        } else {
            mBinding.btSubmit.setText("Câu hỏi tiếp theo");
        }

        QuestionData question = mViewModel.getQuestionDataList().get(mViewModel.getCurrentQuestion());

        String questionText = question.getQuestionId() <= 9 ? "0" + question.getQuestionId() : question.getQuestionId() + "";
        mBinding.questionNumber.setText("Câu hỏi số " + questionText + "/"
                + mViewModel.getQuestionDataList().size());


        mBinding.questionContent.setText(question.getQuestion());
        List<String> answerList = question.getAnswerList();
        updateAnser(answerList);

    }

    private void updateAnser(List<String> answerList) {


        mBinding.rbAnswerA.setText(answerList.get(0));
        mBinding.rbAnswerB.setText(answerList.get(1));
        mBinding.rbAnswerC.setText(answerList.get(2));
        mBinding.rbAnswerD.setText(answerList.get(3));
    }


    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.iv_back) {
            backToPreviousFragment();
        } else if (v.getId() == R.id.bt_submit) {
            doClickButtonSubmit();
        }
    }

    private void doClickButtonSubmit() {

        int count = 0;
        for (int i = 0; i < mViewModel.getRadioButtonList().size(); i++) {

            if (!mViewModel.getRadioButtonList().get(i).isChecked()) {
                count++;
            } else {
                int answrId = (int) mViewModel.getRadioButtonList().get(i).getTag();
                Log.i(TAG, "doClickButtonSubmit: " + answrId);
                mViewModel.updateChooseAnswerList(answrId);
                Log.i(TAG, "doClickButtonSubmit: answered list " + mViewModel.getChooseAnswerList().size());
                mViewModel.setCurrentQuestion(mViewModel.getCurrentQuestion() + 1);
                Log.i(TAG, "doClickButtonSubmit: currentQuestion " + mViewModel.getCurrentQuestion());

                mBinding.rgRadioGroup.clearCheck();
                if (mViewModel.getCurrentQuestion() >= mViewModel.getQuestionDataList().size()) {
                    mainCallBack.showFragment(ResultFragment.TAG, new ExamResult(mViewModel.getChooseAnswerList(), mViewModel.getQuestionDataList()), false);

                } else {
                    mBinding.lnShowSnackBar.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.enter_from_right));
                    showExam();

                }
                return;
            }
        }
        if (count == mViewModel.getRadioButtonList().size()) {
            showSnackbar(mBinding.lnShowSnackBar, "Vui lòng chọn một đáp án rồi tiếp tục", true);

        }


    }

    @Override
    public void onCallbackError(String key, String msg) {
        super.onCallbackError(key, msg);
        showSnackbar(mBinding.lnExamMain, msg, true);
    }

}
