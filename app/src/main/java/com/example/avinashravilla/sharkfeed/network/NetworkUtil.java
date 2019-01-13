package com.example.avinashravilla.sharkfeed.network;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.avinashravilla.sharkfeed.activities.App;

public class NetworkUtil extends ContextWrapper {

    private static NetworkUtil networkUtil;

    public NetworkUtil(Context base) {
        super(base);
    }

    public NetworkUtil getInstance() {
        if (networkUtil == null) {
            networkUtil = new NetworkUtil(App.getContext());
        }
        return networkUtil;
    }


    /**
     * Checks if device is connected to network.
     *
     * @param context the context
     * @return true, if is online
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null) {
            return netInfo.isConnectedOrConnecting();
        }
        return false;
    }
}
