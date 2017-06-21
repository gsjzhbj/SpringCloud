package com.gson;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface Builder {
        void  setName(States states);
        States getName();
        StateMachine<States, Events> build(States states) throws Exception;

}
