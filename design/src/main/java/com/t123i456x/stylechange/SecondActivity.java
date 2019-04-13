package com.t123i456x.stylechange;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private List<Color> colorList = new ArrayList<Color>();
    private SwipeRefreshLayout swipeRefresh;
    private ColorAdapter adapter;
    private int first = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb0);
        toolbar.setTitle("列表");
        setSupportActionBar(toolbar);

        initColors();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv0);
        GridLayoutManager layoutManager = new
                GridLayoutManager(this,2);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ColorAdapter(colorList);
        recyclerView.setAdapter(adapter);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.black,R.color.white);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(first < 3){
                        try {
                            initColors_refresh();
                            adapter.notifyDataSetChanged();
                            swipeRefresh.setRefreshing(false);
                            Toast.makeText(SecondActivity.this, "加载完成！", Toast.LENGTH_SHORT).show();
                            first = first + 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                            swipeRefresh.setRefreshing(false);
                            Toast.makeText(SecondActivity.this, "加载未完成！", Toast.LENGTH_SHORT).show();
                        }
                        }else{
                            swipeRefresh.setRefreshing(false);
                            Toast.makeText(SecondActivity.this, "已全部加载！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    private void initColors() {
        colorList.clear();
        for (int i = 0; i < 1; i++){
            Color a1 = new Color("选项1", R.drawable.rv1);
            colorList.add(a1);
            Color a2 = new Color("选项2", R.drawable.rv1);
            colorList.add(a2);
            Color a3 = new Color("选项3", R.drawable.rv1);
            colorList.add(a3);
            Color a4 = new Color("选项4", R.drawable.rv1);
            colorList.add(a4);
            Color a5 = new Color("选项5", R.drawable.rv1);
            colorList.add(a5);
            Color a6 = new Color("选项6", R.drawable.rv1);
            colorList.add(a6);
            Color a7 = new Color("选项7", R.drawable.rv1);
            colorList.add(a7);
            Color a8 = new Color("选项8", R.drawable.rv1);
            colorList.add(a8);
            Color a9 = new Color("选项9", R.drawable.rv1);
            colorList.add(a9);
            Color a10 = new Color("选项10", R.drawable.rv1);
            colorList.add(a10);
            Color a11 = new Color("选项11", R.drawable.rv1);
            colorList.add(a11);
            Color a12 = new Color("选项12", R.drawable.rv1);
            colorList.add(a12);
        }}

    private void initColors_refresh() {
        for (int i = 0; i < 1; i++){
            int j = (first+2)*6;
            Color a13 = new Color("选项"+(j+1), R.drawable.rv1);
            colorList.add(a13);
            if(j<24){
            Color a14 = new Color("选项"+(j+2), R.drawable.rv1);
            colorList.add(a14);
            Color a15 = new Color("选项"+(j+3), R.drawable.rv1);
            colorList.add(a15);
            Color a16 = new Color("选项"+(j+4), R.drawable.rv1);
            colorList.add(a16);
            Color a17 = new Color("选项"+(j+5), R.drawable.rv1);
            colorList.add(a17);
            Color a18 = new Color("选项"+(j+6), R.drawable.rv1);
            colorList.add(a18);}
        }}

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
