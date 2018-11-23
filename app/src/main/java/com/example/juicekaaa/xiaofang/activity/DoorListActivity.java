package com.example.juicekaaa.xiaofang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.juicekaaa.xiaofang.R;
import com.example.juicekaaa.xiaofang.imp.CallBackImp;
import com.example.juicekaaa.xiaofang.util.MessageEvent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoorListActivity extends AppCompatActivity {
    @BindView(R.id.lv_door)
    ListView lvDoor;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private List<String> mData;
    private ArrayAdapter<String> adapter;
    private static final int REFUSH_DOOR = 0x321;
    private CallBackImp callBackImp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.door_list);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        callBackImp = new CallBackImp();
        initList();

        lvDoor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(DoorListActivity.this, OpenActivity.class);
                    intent.putExtra("mac", "080027109497");
                    startActivity(intent);
                }

            }
        });

        //刷新操作
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                callBackImp.getDoorList();
                adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });

    }

    private void initList() {
        mData = new ArrayList<>();
        mData.add("三棱科技");
        adapter = new ArrayAdapter<String>(DoorListActivity.this, android.R.layout.simple_expandable_list_item_1, mData);
        lvDoor.setAdapter(adapter);
    }


    //获取回调箱子数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refushData(MessageEvent messageEvent) {
        switch (messageEvent.getTAG()) {
            case REFUSH_DOOR:
                mData.clear();
                mData = messageEvent.getListMessage();
                break;
        }

    }

}
