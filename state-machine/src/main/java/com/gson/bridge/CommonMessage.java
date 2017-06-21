package com.gson.bridge;

/**
 * Created by Administrator on 2017/6/20.
 */
public class CommonMessage extends AbstractMessage {

    public CommonMessage(Message impl) {
        super(impl);
    }
    @Override
    public void sendMessage(String message, String toUser) {
        // 对于普通消息，直接调用父类方法，发送消息即可
        super.sendMessage(message, toUser);
    }
}
