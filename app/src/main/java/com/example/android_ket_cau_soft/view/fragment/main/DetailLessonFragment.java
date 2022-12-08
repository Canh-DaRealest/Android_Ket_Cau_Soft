package com.example.android_ket_cau_soft.view.fragment.main;

import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MediaController;

import com.bumptech.glide.Glide;
import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.adapter.ItemLessonPathAdapter;
import com.example.android_ket_cau_soft.databinding.FragmentDetailLessonBinding;
import com.example.android_ket_cau_soft.model.DetailLessonData;
import com.example.android_ket_cau_soft.model.IntentResult;
import com.example.android_ket_cau_soft.model.lesson.ItemLesson;
import com.example.android_ket_cau_soft.model.lesson.ItemLessonPath;
import com.example.android_ket_cau_soft.model.lesson.LessonData;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.DetailLessonVM;

import java.util.List;
import java.util.Objects;

public class DetailLessonFragment extends BaseFragment<FragmentDetailLessonBinding, DetailLessonVM> {
    public static final String TAG = DetailLessonFragment.class.getName();


    @Override
    protected Class<DetailLessonVM> getClassVM() {
        return DetailLessonVM.class;
    }

    @Override
    protected FragmentDetailLessonBinding initViewBinding(LayoutInflater inflater) {
        return FragmentDetailLessonBinding.inflate(inflater);
    }

    @Override
    protected void initView() {

        IntentResult intentResult = (IntentResult) mData;

        String name = (String) intentResult.getName().toString();

        ItemLesson itemLesson = (ItemLesson) intentResult.getData();
        Log.i(TAG, "initView: " + itemLesson.getName());
        mBinding.includeDetailLesson.tvBackText.setText(name);
        mViewModel.setItemLesson(itemLesson);


        mBinding.btNextLeson.setOnClickListener(this);
        mBinding.btPreviousLesson.setOnClickListener(this);
        mBinding.includeDetailLesson.ivBack.setOnClickListener(this);

        if (mViewModel.getAccount() != null) {

            mViewModel.checkToken(mViewModel.getAccount().getEmail(), mViewModel.getAccount().getApiToken());
        } else {
            mViewModel.updateAccountFromDB();
            mViewModel.checkToken(mViewModel.getAccount().getEmail(), mViewModel.getAccount().getApiToken());
        }


    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.GET_DETAIL_LESSON.getEnumValue())) {
            DetailLessonData itemData = (DetailLessonData) data;
            mViewModel.setCurrentLessonData(itemData);
            String url = mViewModel.getCurrentLessonData().getClip_link();
            Uri uri = Uri.parse(url);

            mBinding.tvLessonName.setText(mViewModel.getCurrentLessonData().getName());
            mBinding.lessonContent.setText(mViewModel.getCurrentLessonData().getContent());

            Glide.with(mContext).load(mViewModel.getCurrentLessonData().getClip_cover()).into(mBinding.ivThumnailVideo);
            mBinding.videoView.setVideoURI(uri);

            MediaController ctlr = new MediaController(mContext);
            ctlr.setAnchorView(mBinding.videoView);
            ctlr.setMediaPlayer(mBinding.videoView);
            mBinding.videoView.setMediaController(ctlr);
            setOnRepaireVideo();
            setOnErroVideo();
            //   mBinding.videoView.setZOrderOnTop(true);

        }
    }

    private void setOnRepaireVideo() {
        mBinding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mBinding.ivThumnailVideo.setVisibility(View.GONE);
                mBinding.videoView.setVisibility(View.VISIBLE);
                mBinding.progressbar.setVisibility(View.GONE);
                mp.start();

            }
        });
    }

    private void setOnErroVideo() {
        mBinding.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                getNoticeDialog(mContext).setUpDialog(" Thông báo", "Error: " + what + ", " + extra, "Ok", null, true, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dismissNoticeDialog();
                        backToPreviousFragment();
                    }
                });
                showNoticeDialog();
                return true;
            }
        });
    }

    @Override
    public void onCallbackError(String key, String msg) {
        super.onCallbackError(key, msg);
        if (key.equals(EnumStorage.GET_DETAIL_LESSON.getEnumValue())) {

            getNoticeDialog(mContext).setUpDialog(" Thông báo", msg, "Ok", null, true, new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dismissNoticeDialog();
                    backToPreviousFragment();
                }
            });
            showNoticeDialog();
        } else {
            showSnackbar(mBinding.lnLessonDetail, msg, true);
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
            backToPreviousFragment();
        } else if (v.getId() == R.id.bt_next_leson) {

            if (!Objects.equals(mViewModel.getCurrentLessonData().getNext_id(), mViewModel.getItemLesson().getId())) {
                mViewModel.getDetailOfLesson(mViewModel.getCurrentLessonData().getNext_id(), mViewModel.getAccount().getApiToken());
            }
        } else if (v.getId() == R.id.bt_previous_lesson) {

            if (!Objects.equals(mViewModel.getCurrentLessonData().getPre_id(), mViewModel.getItemLesson().getId())) {
                mViewModel.getDetailOfLesson(mViewModel.getCurrentLessonData().getPre_id(), mViewModel.getAccount().getApiToken());
            }
        }
    }

    private void addLesson(LessonData lessonData) {
        List<ItemLessonPath> itemLessonPathList = lessonData.getLessonList();


        if (itemLessonPathList != null) {

            Log.i(TAG, "addLesson: " + itemLessonPathList.size());
            ItemLessonPathAdapter itemLessonPathAdapter = new ItemLessonPathAdapter(mContext, itemLessonPathList);
//            mBinding.rvLessonPath.setAdapter(itemLessonPathAdapter);
//            mBinding.rvLessonPath.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));

        }
    }

    private void doClickItemLesson(Object tag) {
    }


}
