package com.example.juicekaaa.xiaofang.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.juicekaaa.xiaofang.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 活动具体展示页
 */
public class ShowActivity extends AppCompatActivity {


    @BindView(R.id.iv_use_titile)
    ImageView ivUseTitile;
    @BindView(R.id.tv_use_title)
    TextView tvUseTitle;
    @BindView(R.id.vv_use)
    VideoView vvUse;
    @BindView(R.id.iv_use_conetnt)
    ImageView ivUseConetnt;
    @BindView(R.id.tv_use_content)
    TextView tvUseContent;
    @BindView(R.id.card_introduce)
    CardView cardIntroduce;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_show);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Uri uri = Uri.parse("http://10.101.80.113:8080/RootFile/Platform/20181114/1542178640266.mp4");
        vvUse.setMediaController(new MediaController(this));
//        vvUse.setVideoURI(uri);//"android.resource://" + getPackageName() + "/"+R.raw.sanleng
        vvUse.setVideoURI(uri);
        vvUse.start();
//        Glide.with(ShowActivity.this).load(R.drawable.ganfen).into(ivUseConetnt);
        cardIntroduce.setRadius(8);//设置图片圆角的半径大小
        cardIntroduce.setCardElevation(8);//设置阴影部分大小
        cardIntroduce.setContentPadding(5, 5, 5, 5);//设置图片距离阴影大小
        tvUseContent.setText("  " + getResources().getString(R.string.use_ganfen));
    }
}
