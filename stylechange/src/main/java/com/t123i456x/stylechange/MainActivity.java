package com.t123i456x.stylechange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static com.t123i456x.stylechange.ToastUtils.showCustomToastCenter;

public class MainActivity extends AppCompatActivity {

    public int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.bn1:

                break;
            case R.id.bn2:

                break;
            case R.id.bn3:

                break;
            case R.id.bn4:

                break;
            case R.id.tv1:
//                ToastUtils.showToast(MainActivity.this,"Hello,World!");
//                showCustomImgToast(MainActivity.this,"Hello,World!");
                if(i == 0) {
                    showCustomToastCenter(MainActivity.this, "添加收藏!", android.R.drawable.star_big_on);
                    i=1;
                }else{
                    showCustomToastCenter(MainActivity.this, "取消收藏!", android.R.drawable.star_big_off);
                    i=0;
                }
                break;
        }
    }

}
