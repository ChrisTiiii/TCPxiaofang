package com.example.juicekaaa.xiaofang.util;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/11/16 0016
 * Description:
 **/
public class MessageEvent {
    private int TAG;
    private String message;
    private List<String> listMessage;

    public MessageEvent(int TAG, List<String> listMessage) {
        this.TAG = TAG;
        this.listMessage = listMessage;
    }

    public MessageEvent(int TAG, String message) {
        this.TAG = TAG;
        this.message = message;
    }

    public List<String> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<String> listMessage) {
        this.listMessage = listMessage;
    }

    public int getTAG() {
        return TAG;
    }

    public void setTAG(int TAG) {
        this.TAG = TAG;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
