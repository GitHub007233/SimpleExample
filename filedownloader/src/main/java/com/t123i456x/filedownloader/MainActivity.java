package com.t123i456x.filedownloader;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button bn0;
    private Button bn1;
    private ImageView iv0;
    private Button bn2;
    private Button bn3;
    private Button bn4;
    private Button bn5;
    private TextView tv0;
    private Button bn6;
    private Button bn7;
    private String version_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv0 = findViewById(R.id.iv0);
        tv0 = findViewById(R.id.tv0);
        bn0 = findViewById(R.id.bn0);
        bn1 = findViewById(R.id.bn1);
        bn2 = findViewById(R.id.bn2);
        bn3 = findViewById(R.id.bn3);
        bn4 = findViewById(R.id.bn4);
        bn5 = findViewById(R.id.bn5);
        bn6 = findViewById(R.id.bn6);
        bn7 = findViewById(R.id.bn7);

        bn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //修改服务器地址和端口
                String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader/qb.jpg";
                File file = new File(path);
                if(!file.exists()){
                    downLoad("http://192.168.199.143:8080/app/qb.jpg", "qb.jpg");
                }else{
                    Toast.makeText(MainActivity.this,"图片文件已存在",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader/qb.jpg";
                File file = new File(path);
                if(file.exists()){
                    loadImage();
                }else{
                    Toast.makeText(MainActivity.this,"图片文件不存在",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //修改服务器地址和端口
                String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader/output.json";
                File file = new File(path);
                if(!file.exists()){
                    downLoad("http://192.168.199.143:8080/app/output.json", "output.json");
                }else{
                    Toast.makeText(MainActivity.this,"文本文件已存在",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader/output.json";
                File file = new File(path);
                if(file.exists()){
                    loadText();
                }else{
                    Toast.makeText(MainActivity.this,"文本文件不存在",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //修改服务器地址和端口
                String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader/app-release.apk";
                File file = new File(path);
                if(!file.exists()){
                    downLoad("http://192.168.199.143:8080/app/app-release.apk", "app-release.apk");
                }else{
                    Toast.makeText(MainActivity.this,"APK文件已存在",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //修改服务器地址和端口
                String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader/app-release.apk";
                File file = new File(path);
                if(file.exists()){
                    try{
                        startInstall(MainActivity.this,path);
                    }catch (Exception e) {
                    e.printStackTrace();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"APK文件不存在",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //修改服务器地址和端口
                String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader/倾杯.mp4";
                File file = new File(path);
                if(!file.exists()){
                    downLoad("http://192.168.199.143:8080/app/倾杯.mp4", "倾杯.mp4");
                }else{
                    Toast.makeText(MainActivity.this,"视频文件已存在",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this,"未实现功能！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader";
        deleteDir(path);
        super.onDestroy();
    }

    public static void downLoad(final String path, final String FileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setReadTimeout(5000);
                    con.setConnectTimeout(5000);
                    con.setRequestProperty("Charset", "UTF-8");
                    con.setRequestMethod("GET");
                    if (con.getResponseCode() == 200) {
                        InputStream is = con.getInputStream();//获取输入流
                        FileOutputStream fileOutputStream = null;//文件输出流
                        if (is != null) {
                            FileUtils fileUtils = new FileUtils();
                            fileOutputStream = new FileOutputStream(fileUtils.createFile(FileName));//指定文件保存路径，代码看下一步
                            byte[] buf = new byte[1024];
                            int ch;
                            while ((ch = is.read(buf)) != -1) {
                                fileOutputStream.write(buf, 0, ch);//将获取到的流写入文件中
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void loadImage() {
        String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader";
        try {
            Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(new File(path, "qb.jpg")));
            iv0.setImageBitmap(bmp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadText() {
        String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader/output.json";
        String readStr = "";
        try {
            FileInputStream fis = new FileInputStream(path);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            readStr = new String(b);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv0.setText(readStr);
        version_info = readStr;
    }

    private Activity mAct = this;
    public static int UNKNOWN_CODE = 2018;

    public void startInstall(Context context, String path) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {

            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);

            }else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O){

            //参数1 上下文, 参数2 在AndroidManifest中的android:authorities值, 参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, "com.t123i456x.filedownloader", new File(path));
            Intent install = new Intent(Intent.ACTION_VIEW);
            //由于没有在Activity环境下启动Activity,设置下面的标签
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            startActivity(install);

        }else{
            Toast.makeText(MainActivity.this,"如若未弹出安装界面请自行安装！",Toast.LENGTH_SHORT).show();
            boolean isGranted = getPackageManager().canRequestPackageInstalls();
            if (isGranted) {
                Intent install = new Intent(Intent.ACTION_VIEW);
                install.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(install);
            }
            else {
                new AlertDialog.Builder(mAct)
                        .setCancelable(false)
                        .setTitle("安装应用需要打开未知来源权限，请去设置中开启权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int w) {
                                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                                mAct.startActivityForResult(intent, UNKNOWN_CODE);
                            }
                        })
                        .show();
            }}
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = Environment.getExternalStorageDirectory().toString() + "/filedownloader/app-release.apk";
        startInstall(MainActivity.this,path);
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
