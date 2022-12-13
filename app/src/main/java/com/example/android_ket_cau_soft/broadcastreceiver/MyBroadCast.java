package com.example.android_ket_cau_soft.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCast extends BroadcastReceiver {

    public static OnNetworkCallback onNetworkCallback;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {


            if (isNetworkAvailable(context)) {
                try {

                    onNetworkCallback.returnNetworkState(true);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    onNetworkCallback.returnNetworkState(false);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        } else {

            Log.i("TAG", "onReceive: " + intent.getAction());
        }

    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    public interface OnNetworkCallback {

        void returnNetworkState(boolean state);
    }
}
