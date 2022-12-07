package com.example.android_ket_cau_soft.view.fragment.main;

import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.databinding.FragmentWebViewBinding;
import com.example.android_ket_cau_soft.view.activity.MainActivity;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.CommonVM;


public class WebViewFragment extends BaseFragment<FragmentWebViewBinding, CommonVM> {
    public static final String TAG = WebViewFragment.class.getName();


    @Override
    protected FragmentWebViewBinding initViewBinding(LayoutInflater inflater) {
        return FragmentWebViewBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        mBinding.includeWebViewActionbar.tvBackText.setText("Quay lại");
        String url = (String) mData;
        setWebViewUI(url);
        setClick();
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }


    private void setClick() {
        mBinding.includeWebViewActionbar.ivBack.setOnClickListener(this);

    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.iv_back) {
            if (mBinding.wvWebView.canGoBack()) {
                mBinding.wvWebView.goBack();
            } else {
                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.getSupportFragmentManager().popBackStack();
            }
        }
    }


    private void setWebViewUI(String url) {
        mBinding.wvWebView.getSettings().setSupportZoom(true);
        mBinding.wvWebView.getSettings().setDomStorageEnabled(true);
        mBinding.wvWebView.getSettings().setBuiltInZoomControls(true);
        mBinding.wvWebView.getSettings().setJavaScriptEnabled(true);
        mBinding.wvWebView.getSettings().setAllowContentAccess(true);

        mBinding.wvWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mBinding.progressbar.setProgress(newProgress);
                if (newProgress == 100) {
                    mBinding.progressbar.setVisibility(View.GONE);

                } else {
                    mBinding.progressbar.setVisibility(View.VISIBLE);
                }
            }

        });
        mBinding.wvWebView.setWebViewClient(new WebViewClient());

        mBinding.wvWebView.loadUrl(url);
    }


}
