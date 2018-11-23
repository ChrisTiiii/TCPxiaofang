package com.example.juicekaaa.xiaofang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.juicekaaa.xiaofang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainsActivity extends AppCompatActivity {

    @BindView(R.id.list_use)
    ListView listUse;
    private String[] titile;

    private int[] img = new int[]{};
    private List<Map<String, Object>> dataList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_list);
        ButterKnife.bind(this);
        initData();
        listUse.setAdapter(new SimpleAdapter(getApplicationContext(), dataList, R.layout.train_list_item, new String[]{"title", "img"}, new int[]{R.id.tv_use_item, R.id.iv_use_item}));
        listUse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(TrainsActivity.this,ShowActivity.class));
                        break;
                        default:
                            Toast.makeText(TrainsActivity.this, "还没有开发出来呢", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        titile = getResources().getStringArray(R.array.hao_use);
        img = new int[titile.length];
        for (int i = 0; i < titile.length; i++)
            img[i] = R.mipmap.ic_launcher;
        dataList = new ArrayList<>();
        for (int i = 0; i < titile.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", titile[i]);
            map.put("img", img[i]);
            dataList.add(map);
        }

    }


}
