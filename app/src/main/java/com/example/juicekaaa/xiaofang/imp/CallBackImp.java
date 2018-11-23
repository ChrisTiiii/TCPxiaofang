package com.example.juicekaaa.xiaofang.imp;

import com.example.juicekaaa.xiaofang.MyApp;
import com.example.juicekaaa.xiaofang.pojo.BackTestData;
import com.example.juicekaaa.xiaofang.pojo.ImageUri;
import com.example.juicekaaa.xiaofang.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallBackImp {
    private Retrofit retrofit;
    private String mac;
    private static final int REFUSH_DOOR = 0x321;

    public CallBackImp(String mac) {
        this.mac = mac;
    }

    public CallBackImp() {
    }

    //获取banner图片信息
    public void getImageUri() {
        retrofit = new Retrofit.Builder().baseUrl(MyApp.IMAGE_BANNER_URI).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        IpInterface ipInterface = retrofit.create(IpInterface.class);
        Call<ImageUri> imageCall = ipInterface.getImageUri("hailiang", "app_firecontrol_owner");
        imageCall.enqueue(new Callback<ImageUri>() {
            @Override
            public void onResponse(Call<ImageUri> call, Response<ImageUri> response) {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    list.add(MyApp.IMAGE_BANNER_URI + response.body().getData().get(i).getUrl());
                }
                EventBus.getDefault().post(new MessageEvent(0x123, list));
            }

            @Override
            public void onFailure(Call<ImageUri> call, Throwable t) {
                System.out.println("图片回传失败：" + t.getMessage());
            }
        });

    }

    //发送开门命令
    public void sendOrder(String msg) {
        retrofit = new Retrofit.Builder().baseUrl(MyApp.ORDER_BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        IpInterface ipInterface = retrofit.create(IpInterface.class);
        Call<ResponseBody> call = ipInterface.getOrderBack(mac, msg);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null)
                    try {
                        System.out.println("数据长度为：" + response.body().bytes().length);
                        System.out.println("数据为：" + response.body().bytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("http请求失败：" + t.getMessage());
            }
        });
    }

    public void getTest() {
        retrofit = new Retrofit.Builder().baseUrl(MyApp.ORDER_BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        IpInterface ipInterface = retrofit.create(IpInterface.class);
        Call<BackTestData> call = ipInterface.getTest();
        System.out.println("访问地址：" + call.request().url());
        call.enqueue(new Callback<BackTestData>() {
            @Override
            public void onResponse(Call<BackTestData> call, Response<BackTestData> response) {
                System.out.println("测试：" + call.request().body());
                System.out.println(response.message());
                if (response.body() != null)
                    System.out.println("返回的数据结果为：" + response.body().getMessage());
            }

            @Override
            public void onFailure(Call<BackTestData> call, Throwable t) {
                System.out.println("测试失败结果：" + t.getMessage());
            }
        });

    }


    //获取门列表信息
    public void getDoorList() {
        retrofit = new Retrofit.Builder().baseUrl(MyApp.ORDER_BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        IpInterface ipInterface = retrofit.create(IpInterface.class);
        ipInterface.getDoorList().enqueue(new Callback<BackTestData>() {
            @Override
            public void onResponse(Call<BackTestData> call, Response<BackTestData> response) {
                EventBus.getDefault().post(new MessageEvent(REFUSH_DOOR, response.body().getMessage()));
            }

            @Override
            public void onFailure(Call<BackTestData> call, Throwable t) {

            }
        });
    }

}
