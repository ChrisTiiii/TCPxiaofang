package com.example.juicekaaa.xiaofang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.juicekaaa.xiaofang.activity.DoorListActivity;
import com.example.juicekaaa.xiaofang.activity.TrainsActivity;
import com.example.juicekaaa.xiaofang.imp.CallBackImp;
import com.example.juicekaaa.xiaofang.util.GlideImageLoader;
import com.example.juicekaaa.xiaofang.util.MessageEvent;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private int icon[];
    private String name[];
    private List<Map<String, Object>> dataList;
    private String from[] = {"img", "name"};
    int to[] = {R.id.option_img, R.id.option_text};
    private CallBackImp callBackImp;
    private ArrayList<String> uriList;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.grid_view)
    GridView gridView;
    @BindView(R.id.cv_main)
    CardView cvMain;

    Intent intent = null;
    private static final int BACK_IMAGE_URI = 0x123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        cvMain.setRadius(8);//设置图片圆角的半径大小
        cvMain.setCardElevation(8);//设置阴影部分大小
        cvMain.setContentPadding(5, 5, 5, 5);//设置图片距离阴影大小

        gridView.setAdapter(new SimpleAdapter(this, dataList, R.layout.option_item, from, to));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        intent = new Intent(getApplicationContext(), DoorListActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(getApplicationContext(), TrainsActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "还没开发出来呢", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //初始化banner
    private void initBanner() {
        System.out.println("初始化获取urilist：" + uriList.size());
        if (uriList != null) {
            banner.setImages(uriList).setImageLoader(new GlideImageLoader()).start();
            System.out.println(uriList.size());
        }
    }

    //初始化数据
    void initData() {
        name = getResources().getStringArray(R.array.options);
        dataList = new ArrayList<>();
        icon = new int[name.length];
        for (int i = 0; i < name.length; i++) {
            icon[i] = R.mipmap.ic_launcher;
        }
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", icon[i]);
            map.put("name", name[i]);
            dataList.add(map);
        }
        getUri();
    }


    //获取轮播图片资源
    public void getUri() {
        callBackImp = new CallBackImp();
        callBackImp.getImageUri();
    }

    //接收回传数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getImageUri(MessageEvent messageEvent) {
        switch (messageEvent.getTAG()) {
            case BACK_IMAGE_URI:
                uriList = (ArrayList<String>) messageEvent.getListMessage();
                if (uriList.size() > 0)
                    for (int i = 0; i < uriList.size(); i++) {
                        System.out.println("初始化网址：" + uriList.get(i));
                    }
                initBanner();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
