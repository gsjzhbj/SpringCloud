package com.gson.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gaoshja on 2017/6/19.
 */
public enum OrderStates {
    INITED("初始化","0"),
    UNPAID("待支付","1"),
    WAITING_FOR_RECEIVE("待收货","2"),
    DONE("结束","10"),
    CANCEL_BUYER("买家取消","-1"),
    CANCEL_SELLER("卖家取消","-2"),
    WAITING_FOR_PENDING("待发货","3"),
    STOCK;
    private String desc;
    private String value;
    private static Logger logger= LoggerFactory.getLogger("state");
    OrderStates(String desc,String value) {
        this.desc=desc;
        this.value=value;
    }

    OrderStates() {
        this.desc="";
        this.value="0";
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String []args){
        logger.info("{} {} {}",UNPAID,UNPAID.desc,UNPAID.value);
        OrderStates state=UNPAID;
        switch (state){
            case UNPAID:
                System.out.println(UNPAID.desc);
                break;
        }
    }
}
