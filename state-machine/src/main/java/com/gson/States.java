package com.gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaoshja on 2017/6/15.
 */
public enum States {
    //
    INITED,
    // 待支付
    UNPAID,
    // 待收货
    WAITING_FOR_RECEIVE,
    // 结束
    DONE,
    // 买家取消
    CANCEL_BUYER,
    //卖家取消
    CANCEL_SELLER,
    //待发货
    WAITING_FOR_PENDING;

    private final static Map<String , States> ENUM_MAP = new HashMap<String, States>(64);

    static {
        for(States v : values()) {
            ENUM_MAP.put(v.toString() , v);
        }
    }

    public static States fromString(String v) {
        States states = ENUM_MAP.get(v);
        return states == null ? UNPAID :states;
    }

    public static String toString(States states){
        return states.toString();
    }
}