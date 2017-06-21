package com.gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
public class EventConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @OnTransition(target = "UNPAID")
    public void create() {
        logger.info("create 订单创建，待支付");
        States.toString(States.WAITING_FOR_PENDING);
    }
    @OnTransition(source = "UNPAID", target = StatesConst.WAITING_FOR_PENDING)
    public void delayPay() {
        logger.info("delayPay 用户货到付款，待发货");
    }
    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        logger.info("pay 用户完成支付，待收货");
    }
    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
        logger.info("receive 用户已收货，订单完成");
    }
    @OnTransition(source = "WAITING_FOR_PENDING",target = "CANCEL_SELLER")
    public void cancel(){
        logger.info("卖家取消");
    }
}
