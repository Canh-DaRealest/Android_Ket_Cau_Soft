package com.example.android_ket_cau_soft.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_ket_cau_soft.R;


public class CustomProgressDialog extends Dialog {

				public CustomProgressDialog(@NonNull Context context) {
								super(context);

								initViews();
				}

				private void initViews() {

								getWindow().setBackgroundDrawableResource(R.drawable.bg_transparent);
								setCancelable(false);
								setCanceledOnTouchOutside(false);
								setContentView(R.layout.progress_dialog);
								getWindow().setGravity(Gravity.CENTER);
								getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

				}

				public CustomProgressDialog(@NonNull Context context, int themeResId) {
								super(context, themeResId);
								initViews();
				}

				protected CustomProgressDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
								super(context, cancelable, cancelListener);
								initViews();
				}


}
