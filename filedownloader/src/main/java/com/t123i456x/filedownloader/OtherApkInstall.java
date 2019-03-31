package com.t123i456x.filedownloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class OtherApkInstall extends BroadcastReceiver {

    //监听其他应用更新，安装，卸载

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String packageName = intent.getDataString();
            Log.e("Test", "---------------" + "PACKAGE_REMOVED" + packageName);
            Toast.makeText(context, "完成卸载"+packageName, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            Log.e("Test", "---------------" + "PACKAGE_ADDED" + packageName);
            Toast.makeText(context, "完成安装"+packageName, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals("android.intent.action.PACKAGE_REPLACED")) {
            String packageName = intent.getDataString();
            Log.e("Test", "---------------" + "PACKAGE_REPLACED" + packageName);
            Toast.makeText(context, "完成更新"+packageName, Toast.LENGTH_LONG).show();
        }
    }

}
