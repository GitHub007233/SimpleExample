package com.t123i456x.installapk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private InstallUtil mInstallUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInstallUtil = new InstallUtil(this,
                Environment.getExternalStorageDirectory().toString() + "/filedownloader/app-release.apk");
        mInstallUtil.install();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == InstallUtil.UNKNOWN_CODE) {
            mInstallUtil.install();//再次执行安装流程，包含权限判等
        }
    }

}
