package com.example.android_ket_cau_soft.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.databinding.DialogNoticeBinding;

public class NoticeDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private DialogNoticeBinding binding;

    public NoticeDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        binding = DialogNoticeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {


    }

    public void setUpDialog(String title, String content, String btPositive, String btNegative, boolean isCancelable, View.OnClickListener onClickListener) {

        if (title != null) {
            binding.tvTitle.setText(title);
        } else {
            binding.tvTitle.setVisibility(View.GONE);
        }

        if (content != null) {
            binding.tvContent.setText(content);
        }

        if (btPositive == null) {
            binding.btPositive.setVisibility(View.GONE);
            binding.btNegative.setOnClickListener(this);

        } else {
            binding.btPositive.setText(btPositive);
        }

        if (btNegative != null) {
            binding.btNegative.setText(btNegative);
        } else {
            binding.btPositive.setOnClickListener(onClickListener);
            binding.btNegative.setVisibility(View.GONE);
        }

        if (onClickListener != null) {

                binding.btPositive.setOnClickListener(onClickListener);
                binding.btNegative.setOnClickListener(onClickListener);

        } else {
            binding.btNegative.setOnClickListener(onClickListener);
        }

        setCancelable(isCancelable);
        setCanceledOnTouchOutside(isCancelable);


    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public NoticeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NoticeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
