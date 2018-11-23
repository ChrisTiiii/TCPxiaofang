package com.example.juicekaaa.xiaofang.imp;

import com.example.juicekaaa.xiaofang.pojo.BackTestData;
import com.example.juicekaaa.xiaofang.pojo.ImageUri;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IpInterface {
    @POST("/emergencystation/C/54C9DFF77FAB")//080027109497  54C9DFF77FAB
    Call<BackTestData> getTest();

    @GET("/")
    Call<ResponseBody> getOrderBack(@Query("mac") String mac, @Query("order") String order);//接收网络请求数据的方法

    @GET("/kspf/app/publicityedu/banner")
    Call<ImageUri> getImageUri(@Query("username") String username, @Query("platformkey") String platformkey);


    @GET("/")
    Call<BackTestData> getDoorList();//接收门的列表
}
