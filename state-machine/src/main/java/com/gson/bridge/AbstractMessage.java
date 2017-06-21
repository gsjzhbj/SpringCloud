package com.gson.bridge;

/**
 * Created by Administrator on 2017/6/20.
 */
public class AbstractMessage {
    Message message;

    /**
     * 构造方法，传入实现部分的对象
     *
     * @param message 实现部分的对象
     */
    public AbstractMessage(Message message) {
        this.message = message;
    }

    /**
     * 发送消息，委派给实现部分的方法
     *
     * @param message 要发送消息的内容
     * @param toUser  消息的接受者
     */
    public void sendMessage(String message, String toUser) {
        this.message.send(message, toUser);
    }
}
