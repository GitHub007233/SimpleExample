package com.t123i456x.stylechange;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.FileNameGenerator;

import java.io.File;
import java.util.HashMap;

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

    protected int i = 1;
    protected int j = 1;
    protected int k = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        et0 = (EditText) findViewById(R.id.et0);

        videoView = (VideoView) findViewById(R.id.video).findViewById(R.id.videoview);
        seekBar = (SeekBar) findViewById(R.id.video).findViewById(R.id.seekBar);
        play0 = (ImageButton) findViewById(R.id.video).findViewById(R.id.play);
        over = (TextView)  findViewById(R.id.video).findViewById(R.id.over);
        name = (TextView)  findViewById(R.id.video).findViewById(R.id.name);
        start = (TextView)  findViewById(R.id.video).findViewById(R.id.start);
        A0 = (LinearLayout)  findViewById(R.id.video).findViewById(R.id.A0);
        A3 = (LinearLayout)  findViewById(R.id.video).findViewById(R.id.A3);
        A2 = (RelativeLayout)  findViewById(R.id.video).findViewById(R.id.A2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                videoView.start();
                play0.setImageResource(R.drawable.ic_pause);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                videoView.pause();
                play0.setImageResource(R.drawable.ic_play);
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                if(fromUser){
                    videoView.seekTo(progress);
                }
            }});

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                videoView.suspend();
                Toast.makeText(Video.this,"视频播放完成！",Toast.LENGTH_SHORT).show();
                play0.setImageResource(R.drawable.ic_play);
            }
        });

        A0.setVisibility(View.INVISIBLE);
        videoView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT){
                    if(j==1){
                        if (i==1) {
                            final Animation translate=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate);//获取平移动画资源
                            A0.startAnimation(translate);
                            final Animation translate_x=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_x);//获取平移动画资源
                            A2.startAnimation(translate_x);
                            final Animation translate_s=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_s);//获取平移动画资源
                            A3.startAnimation(translate_s);
                            A0.setVisibility(View.INVISIBLE);
                            A2.setVisibility(View.INVISIBLE);
                            A3.setVisibility(View.INVISIBLE);
                            i = 0;
                        } else {
                            A0.setVisibility(View.VISIBLE);
                            A2.setVisibility(View.VISIBLE);
                            A3.setVisibility(View.VISIBLE);
                            final Animation translate=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_f);//获取平移动画资源
                            A0.startAnimation(translate);
                            final Animation translate_xf=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_xf);//获取平移动画资源
                            A2.startAnimation(translate_xf);
                            final Animation translate_sf=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_sf);//获取平移动画资源
                            A3.startAnimation(translate_sf);
                            i = 1;
                        }}else{
                        if(k==1) {
                            A0.setVisibility(View.VISIBLE);
                            final Animation translate = AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_f);//获取平移动画资源
                            A0.startAnimation(translate);
                            k = 0;
                        }else{
                            final Animation translate=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate);//获取平移动画资源
                            A0.startAnimation(translate);
                            A0.setVisibility(View.INVISIBLE);
                            k = 1;
                        }
                    }
                }else{
                    if(j==1){
                        if (i==1) {
                            final Animation translate_x=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_x);//获取平移动画资源
                            A2.startAnimation(translate_x);
                            final Animation translate_s=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_s);//获取平移动画资源
                            A3.startAnimation(translate_s);
                            A2.setVisibility(View.INVISIBLE);
                            A3.setVisibility(View.INVISIBLE);
                            i = 0;
                        } else {
                            A2.setVisibility(View.VISIBLE);
                            A3.setVisibility(View.VISIBLE);
                            final Animation translate_xf=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_xf);//获取平移动画资源
                            A2.startAnimation(translate_xf);
                            final Animation translate_sf=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_sf);//获取平移动画资源
                            A3.startAnimation(translate_sf);
                            i = 1;
                        }}else{
                        if(k==1) {
                            A0.setVisibility(View.VISIBLE);
                            final Animation translate = AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_f);//获取平移动画资源
                            A0.startAnimation(translate);
                            k = 0;
                        }else{
                            final Animation translate=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate);//获取平移动画资源
                            A0.startAnimation(translate);
                            A0.setVisibility(View.INVISIBLE);
                            k = 1;
                        }
                    }
                }

                return false;
            }
        });

        onSystemUiVisibilityChange(View.INVISIBLE);

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

    private Uri url;
    private String URL;
    private VideoView videoView = null;
    private SeekBar seekBar;
    private ImageButton play0;
    private  String videoUrl;
    private EditText et0;
    private int color;
    private String duration;
    private TextView over;
    private TextView start;
    private TextView name;
    private LinearLayout A0;
    private LinearLayout A3;
    private RelativeLayout A2;

    private HttpProxyCacheServer getProxy() {
        return App.getProxy(getApplicationContext());
    }

    public static class MyFileNameGenerator implements FileNameGenerator {
        public String generate(String url) {
            File video =new File( url.trim());
            String videoId = video.getName();
            return videoId ;
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.bn0:
                if(et0.getText().toString().equals("")){
                    videoUrl = "http://192.168.199.143:8081/dm/Re0/td/25.mp4";
                    et0.setText(videoUrl);
                }else{
                    URL = et0.getText().toString();
                    MyFileNameGenerator nameGenerator = new MyFileNameGenerator();
                    nameGenerator.generate(URL);
                    url = Uri.parse( et0.getText().toString() );
                    HttpProxyCacheServer proxy = getProxy();
                    String proxyUrl = proxy.getProxyUrl(et0.getText().toString());

                    //设置视频路径
                    //videoView.setVideoURI(url);
                    videoView.setVideoPath(proxyUrl);

                    try {
                        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                        retriever.setDataSource(et0.getText().toString(), new HashMap<String, String>());
                        duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                        retriever.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    seekBar.setMax(Integer.parseInt(duration));
                    over.setText(toTime(Integer.parseInt(duration)));
                    //开始播放视频
                    videoView.start();
                    String number = et0.getText().toString();
                    if(number.startsWith("http://192.168.199.143:8081/dm/Re0/")){
                        name.setText("Re0-从零开始的异世界生活");
                    }
                    handler.post(run);
                    play0.setImageResource(R.drawable.ic_pause);
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
            case R.id.play:
                if(!et0.getText().toString().equals("")){
                if(!videoView.isPlaying()) {
                    play0.setImageResource(R.drawable.ic_pause);
                    videoView.start();
                }else{
                    play0.setImageResource(R.drawable.ic_play);
                    videoView.pause();
                }}else{
                    Toast.makeText(this, "无法加载视频！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.next:
                if(videoView.isPlaying()) {
                    videoView.pause();
                    seekBar.setProgress(videoView.getCurrentPosition() + 10000);
                    videoView.seekTo(seekBar.getProgress());
                    videoView.start();
                }
                break;
            case R.id.last:
                if(videoView.isPlaying()) {
                    videoView.pause();
                    seekBar.setProgress(videoView.getCurrentPosition() - 10000);
                    videoView.seekTo(seekBar.getProgress());
                    videoView.start();
                }
                break;
            case R.id.set:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.back:
                Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sd:
                if (j==1) {
                    if(getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT){
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }else{
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }
                    final Animation translate_x=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_x);//获取平移动画资源
                    A2.startAnimation(translate_x);
                    final Animation translate_s=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_s);//获取平移动画资源
                    A3.startAnimation(translate_s);
                    A2.setVisibility(View.INVISIBLE);
                    A3.setVisibility(View.INVISIBLE);
                    j = 0;
                    final Animation translate=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate);//获取平移动画资源
                    A0.startAnimation(translate);
                    A0.setVisibility(View.INVISIBLE);
                } else {
                    if(getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT){
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                    }else{
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                    }
                    A2.setVisibility(View.VISIBLE);
                    A3.setVisibility(View.VISIBLE);
                    final Animation translate_xf=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_xf);//获取平移动画资源
                    A2.startAnimation(translate_xf);
                    final Animation translate_sf=AnimationUtils.loadAnimation(Video.this, R.anim.anim_translate_sf);//获取平移动画资源
                    A3.startAnimation(translate_sf);
                    j = 1;
                    i = 1;
                    k = 1;
                }
                break;
            case R.id.qp:
                if(getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                }else{
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                }
                break;
            case R.id.fp:
                Toast.makeText(this, "分屏", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    Handler handler=new Handler();
    Runnable run= new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(videoView.getCurrentPosition());
            start.setText(toTime(videoView.getCurrentPosition()));
            handler.postDelayed(run, 200);
        }};

    public static String toTime(int time) {
        time /= 1000;
        int minute = time / 60;
        int second = time % 60;
        minute %= 60;
        //格式化时间
        return String.format(" %02d:%02d ", minute, second);
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
            A0.setVisibility(View.VISIBLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            findViewById(R.id.ln0).setVisibility(View.GONE);
            findViewById(R.id.ln2).setVisibility(View.GONE);
            ViewGroup.LayoutParams lp;
            RelativeLayout ln1 = (RelativeLayout)findViewById(R.id.ln1);
            lp = ln1.getLayoutParams();
            lp.width=width;
            lp.height=height;
        }else{
            A0.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            findViewById(R.id.ln0).setVisibility(View.VISIBLE);
            findViewById(R.id.ln2).setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams lp;
            RelativeLayout ln1 = (RelativeLayout)findViewById(R.id.ln1);
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
    public void onBackPressed(){
        if(getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        }else{
            finish();
        }
    }

    private boolean play = false;
    public int currenttime;
    @Override
    protected void onPause() {
        if(videoView.isPlaying()){
            currenttime=videoView.getCurrentPosition();
            play=true;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if(play){
            videoView.seekTo(currenttime-1000);
            videoView.start();
            play=false;}
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView != null){
            videoView.suspend();
        }
    }

    public void onSystemUiVisibilityChange(int visibility) {
        if (visibility==View.SYSTEM_UI_FLAG_FULLSCREEN||visibility==View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN) {
            Log.i("TAG","全屏状态======");
        }else{
            Log.i("TAG","非全屏状态======");
        }
    }

}
