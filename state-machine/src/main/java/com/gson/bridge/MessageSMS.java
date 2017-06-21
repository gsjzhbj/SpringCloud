package com.gson.bridge;

/**
 * Created by Administrator on 2017/6/20.
 */
public class MessageSMS implements Message {

    @Override
    public void send(String message, String toUser) {

        System.out.println("使用系统内短消息的方法，发送消息'" + message + "'给" + toUser);
    }

}
