package com.example.juicekaaa.xiaofang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.juicekaaa.xiaofang.R;
import com.example.juicekaaa.xiaofang.imp.CallBackImp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenActivity extends AppCompatActivity {
    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;
    @BindView(R.id.btn_three)
    Button btnThree;
    @BindView(R.id.btn_four)
    Button btnFour;

    private CallBackImp callBackImp;
    private static String mac;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_door);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mac = intent.getExtras().getString("mac");
        System.out.println("mac:" + mac);
        callBackImp = new CallBackImp(mac);
    }

    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                callBackImp.sendOrder("68010102ffff16");
//                new UdpSendThread("68010102ffff16").start();
                break;
            case R.id.btn_two:
                callBackImp.sendOrder("68010202ffff16");
//                 new UdpSendThread("68010202ffff16").start();
                break;
            case R.id.btn_three:
                callBackImp.sendOrder("68010302ffff16");
//                new UdpSendThread("68010302ffff16").start();
                break;
            case R.id.btn_four:
                callBackImp.getTest();
//                callBackImp.sendOrder("68010402ffff16");
//                new UdpSendThread("68010402ffff16").start();
                break;
        }
    }
}
