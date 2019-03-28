package com.t123i456x.filedownloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import java.io.File;

public class DeleteFiles extends BroadcastReceiver {

    private String path = Environment.getExternalStorageDirectory().toString() + "/file";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String packageName = intent.getDataString();
            Log.e("Test", "---------------" + "PACKAGE_REMOVED" + packageName);
            Toast.makeText(context, "完成卸载"+packageName, Toast.LENGTH_LONG).show();
            deleteDir(path);
        }
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            Log.e("Test", "---------------" + "PACKAGE_ADDED" + packageName);
            Toast.makeText(context, "完成安装"+packageName, Toast.LENGTH_LONG).show();
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        if (intent.getAction().equals("android.intent.action.PACKAGE_REPLACED")) {
            String packageName = intent.getDataString();
            Log.e("Test", "---------------" + "PACKAGE_REPLACED" + packageName);
            Toast.makeText(context, "完成更新"+packageName, Toast.LENGTH_LONG).show();
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

}
