package com.gson.bridge;

/**
 * Created by Administrator on 2017/6/20.
 */
public class MessageMail implements Message{
    @Override
    public void send(String message, String toUser) {
        System.out.println("使用邮件短消息的方法，发送消息'"+message+"'给"+toUser);
    }
}
