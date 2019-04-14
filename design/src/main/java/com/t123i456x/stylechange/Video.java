package com.t123i456x.stylechange;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import static android.graphics.Color.argb;

public class Video extends AppCompatActivity {

    public static final String COLOR_NAME = "color_name";
    public static final String COLOR_IMAGE_ID = "color_image_id";
    private SeekBar sb1;
    private SeekBar sb2;
    private SeekBar sb3;
    private SeekBar sb4;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private String red = "00";
    private String green = "00";
    private String blue = "00";
    private String tran = "00";
    private int redi = 0;
    private int greeni = 0;
    private int bluei = 0;
    private int trani = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        et0 = (EditText) findViewById(R.id.et0);
        videoView = (VideoView) findViewById(R.id.videoView );

        sb1 = (SeekBar)findViewById(R.id.sb1);
        tv1=(TextView) findViewById(R.id.tv1);
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 16){
                    red = Integer.toHexString( progress );
                    red = "0"+red;
                }else{
                    red = Integer.toHexString( progress );
                }
                tv1.setText(red);
                redi = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sb2 = (SeekBar)findViewById(R.id.sb2);
        tv2=(TextView) findViewById(R.id.tv2);
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 16){
                    green = Integer.toHexString( progress );
                    green = "0"+green;
                }else{
                    green = Integer.toHexString( progress );
                }
                tv2.setText(green);
                greeni = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sb3 = (SeekBar)findViewById(R.id.sb3);
        tv3=(TextView) findViewById(R.id.tv3);
        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 16){
                    blue = Integer.toHexString( progress );
                    blue = "0"+blue;
                }else{
                    blue = Integer.toHexString( progress );
                }
                tv3.setText(blue);
                bluei = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sb4 = (SeekBar)findViewById(R.id.sb4);
        tv4=(TextView) findViewById(R.id.tv4);
        sb4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 16){
                    tran = Integer.toHexString( progress );
                    tran = "0"+tran;
                }else{
                    tran = Integer.toHexString( progress );
                }
                tv4.setText(tran);
                trani = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Intent intent = getIntent();
        String colorName = intent.getStringExtra(COLOR_NAME);
        int colorImageId = intent.getIntExtra(COLOR_IMAGE_ID, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("视频播放");
        setSupportActionBar(toolbar);
    }

    private Uri uri;
    private VideoView videoView;
    private  String videoUrl;
    private EditText et0;
    private int color;

    public void onClick(View v){
        switch (v.getId()){
            case R.id.bn0:
                if(et0.getText().toString().equals("")){
                    videoUrl = "http://192.168.199.143:8081/dm/Re0/24.mkv";
                    et0.setText(videoUrl);
                }else{
                    uri = Uri.parse( et0.getText().toString() );
                    //设置视频路径
                    videoView.setVideoURI(uri);
                    //开始播放视频
                    videoView.start();
                }
                break;
            case R.id.bn1:
                color = argb(trani,redi, greeni,bluei);
                videoView.setBackgroundColor(color);
                break;
            case R.id.bn2:
                color = argb(trani,redi, greeni,bluei);
                showColorToastCenter(this,color);
                break;
        }
    }

    private Toast toast1 = null;
    public void showColorToastCenter(Context mContext,int color) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.toast_color, null);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setBackgroundColor(color);
        if (toast1 != null) {
            toast1.cancel();
        }
        toast1 = new Toast(mContext);
        toast1.setDuration(Toast.LENGTH_SHORT);
        toast1.setView(view);
        toast1.setGravity(Gravity.CENTER, 0, 0);
        toast1.show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        float density = dm.density;
        int densityDpi = dm.densityDpi;

        if(getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                findViewById(R.id.ln0).setVisibility(View.GONE);
                findViewById(R.id.ln2).setVisibility(View.GONE);
                ViewGroup.LayoutParams lp;
                LinearLayout ln1 = (LinearLayout)findViewById(R.id.ln1);
                lp = ln1.getLayoutParams();
                lp.width=width;
                lp.height=height;
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            findViewById(R.id.ln0).setVisibility(View.VISIBLE);
            findViewById(R.id.ln2).setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams lp;
            LinearLayout ln1 = (LinearLayout)findViewById(R.id.ln1);
            lp = ln1.getLayoutParams();
            lp.width=1000;
            lp.height=1000*9/16;
        }
    }

    //设置标题栏右上方上图标
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_others, menu);
        return true;
    }

    //设置标题栏图标点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                finish();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView != null){
            videoView.suspend();
        }
    }
}
