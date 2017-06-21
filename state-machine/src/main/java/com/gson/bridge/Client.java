package com.gson.bridge;

/**
 * Created by Administrator on 2017/6/20.
 */
public class Client {

    public static void main(String[] args) {
        //创建具体的实现对象
        Message impl = new MessageSMS();
        //创建普通消息对象
        AbstractMessage message = new  CommonMessage(impl);
        message.sendMessage("加班申请速批","李总");

        //将实现方式切换成邮件，再次发送
        impl = new MessageMail();
        //创建加急消息对象
        message = new UrgencyMessage(impl);
        message.sendMessage("加班申请速批","李总");
    }

}
