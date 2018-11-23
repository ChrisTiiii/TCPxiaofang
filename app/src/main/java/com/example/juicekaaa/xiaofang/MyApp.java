package com.example.juicekaaa.xiaofang;

import android.app.Application;


/**
 * author: ZhongMing
 * DATE: 2018/11/16 0016
 * Description:
 **/
public class MyApp extends Application {
    public static final String IMAGE_BANNER_URI = "http://10.101.80.113:8080";//Banner图片uri
    public static final String ORDER_BASE_URL = "http://10.101.80.134:8091";//Order消息发送,心跳包uri


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
