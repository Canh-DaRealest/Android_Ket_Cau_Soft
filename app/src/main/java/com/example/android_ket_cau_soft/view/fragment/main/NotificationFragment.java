package com.example.android_ket_cau_soft.view.fragment.main;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.adapter.HotNewsAdapter;
import com.example.android_ket_cau_soft.api.response.notification.NotifiData;
import com.example.android_ket_cau_soft.databinding.FragmentNotificationBinding;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.home.HomeFragmentVM;

import java.util.List;

public class NotificationFragment extends BaseFragment<FragmentNotificationBinding, HomeFragmentVM> implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = NotificationFragment.class.getName();
    private int count;

    @Override
    protected Class<HomeFragmentVM> getClassVM() {
        return HomeFragmentVM.class;
    }

    @Override
    protected FragmentNotificationBinding initViewBinding(LayoutInflater inflater) {
        return FragmentNotificationBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        mBinding.includeNotifyBack.tvBackText.setText("Thông Báo");
        mBinding.includeNotifyBack.ivBack.setVisibility(View.GONE);

        mBinding.swNotificationLayout.setOnRefreshListener(this);
        checkNetworkConnection();

    }

    private void checkToken() {
        mViewModel.updateAccountFromDB();
        mViewModel.checkToken(mViewModel.getAccount().getEmail(), mViewModel.getAccount().getApiToken());
    }

    private void updateData(List<NotifiData> data) {


        HotNewsAdapter adapter = new HotNewsAdapter(mContext, data);

        adapter.setType(HotNewsAdapter.NOTIFICATION_TYPE);
        mBinding.rvNewsRecyclerview.setAdapter(adapter);
        mBinding.rvNewsRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        mBinding.rvNewsRecyclerview.addItemDecoration(itemDecoration);
        adapter.getLiveData().observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                NotifiData data1 = (NotifiData) o;
                showWebFragment(data1.getLink());
                mViewModel.markAsReaded(data1.getId());
            }
        });

    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);

        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            checkToken();
        } else if (key.equals(EnumStorage.GET_NOTIFICATION.getEnumValue())) {
            count = Integer.parseInt(msg);

            onUpdateCountCallback.updateCount(count);
            List<NotifiData> notifiData = (List<NotifiData>) data;
            updateData(notifiData);
        } else if (key.equals(EnumStorage.MARK_AS_READ.getEnumValue())) {
            if (count <= 0) {
                onUpdateCountCallback.updateCount(0);
            } else {
                onUpdateCountCallback.updateCount(count--);
            }
        }
    }

    @Override
    public void onCallbackError(String key, String msg) {
        super.onCallbackError(key, msg);
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            showSnackbar(mBinding.swNotificationLayout, msg, true);
        } else {
            showSnackbar(mBinding.swNotificationLayout, msg, true);
        }
    }


    private void showWebFragment(String url) {

        onParentFrgCallback.showFragmentFromMenu(WebViewFragment.TAG, url, true);
    }

    @Override
    public void onRefresh() {
        checkToken();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (
                        mBinding.swNotificationLayout.isRefreshing()
                ) {
                    Toast.makeText(mContext, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                    mBinding.swNotificationLayout.setRefreshing(false);
                }
            }
        }, 1000);
    }
}
