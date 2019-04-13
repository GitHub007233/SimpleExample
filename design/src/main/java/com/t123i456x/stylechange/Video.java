package com.t123i456x.stylechange;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Video extends AppCompatActivity {

    public static final String COLOR_NAME = "color_name";
    public static final String COLOR_IMAGE_ID = "color_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();
        String colorName = intent.getStringExtra(COLOR_NAME);
        int colorImageId = intent.getIntExtra(COLOR_IMAGE_ID, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(colorName);
        setSupportActionBar(toolbar);
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
}
