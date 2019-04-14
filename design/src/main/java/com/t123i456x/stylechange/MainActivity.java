package com.t123i456x.stylechange;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

import static com.t123i456x.stylechange.ToastUtils.showCustomToastCenter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    public int i = 0;
    View statusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusBar = findViewById(R.id.statusBarView);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = getStatusBarHeight();

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb0);
        toolbar.setTitle("主页");
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu_black);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0, 0);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Button bn1 = (Button) findViewById(R.id.bn1);
        bn1.setOnTouchListener(new TouchScaleListener(0.90f));
        Button bn2 = (Button) findViewById(R.id.bn2);
        bn2.setOnTouchListener(new TouchScaleListener(0.90f));
        TextView tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnTouchListener(new TouchScaleListener(0.90f));

        final View headerView = navView.getHeaderView(0);
        headerView.findViewById(R.id.headerimage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView icon =(ImageView)headerView.findViewById(R.id.headerimage);
                Toast.makeText(MainActivity.this,"你点击了图片！",Toast.LENGTH_SHORT).show();
            }});

        //nav点击事件
        navView.setCheckedItem(R.id.nav_intro);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_intro:
                        Toast.makeText(MainActivity.this, "你点击了选项1！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_feedback:
                        Toast.makeText(MainActivity.this, "你点击了选项2！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(MainActivity.this, "你点击了选项3！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_update:
                        Toast.makeText(MainActivity.this, "你点击了选项4！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_timing:
                        Toast.makeText(MainActivity.this, "你点击了选项5！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_exit:
                        Toast.makeText(MainActivity.this, "你点击了选项6！", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //设置标题栏右上方上图标
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main, menu);
        return true;
    }

    //设置标题栏图标点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    // 让菜单同时显示图标和文字
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.bn1:
                Toast.makeText(this, "已切换到日间主题", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bn2:
                Toast.makeText(this, "已切换到夜间主题", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bn3:
                dialogImageView();
                break;
            case R.id.bn4:
                    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(intent);
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
            case R.id.settings:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private View mPopupHeadViewy;
    private PopupWindow mHeadPopupclly;
    private TextView textwzdl, textckxq;
    @SuppressWarnings("deprecation")
    private void dialogImageView() {
        mPopupHeadViewy = View.inflate(this, R.layout.dialog, null);
        textwzdl = (TextView) mPopupHeadViewy.findViewById(R.id.textwzdl);
        textckxq = (TextView) mPopupHeadViewy.findViewById(R.id.textckxq);
        mHeadPopupclly = new PopupWindow(mPopupHeadViewy, AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT, true);
        // 在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        mHeadPopupclly.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mHeadPopupclly.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mHeadPopupclly.setBackgroundDrawable(new BitmapDrawable());
        mHeadPopupclly.setOutsideTouchable(true);
        mHeadPopupclly.showAsDropDown(statusBar , 0, 0);
        textwzdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHeadPopupclly.dismiss();
            }
        });
        textckxq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHeadPopupclly.dismiss();
                Intent intent = new Intent(MainActivity.this,Video.class);
                startActivity(intent);
            }
        });
    }


}
