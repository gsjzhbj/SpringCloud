package com.gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.*;
import org.springframework.statemachine.transition.Transition;
import org.springframework.statemachine.listener.StateMachineListener;

import java.util.EnumSet;

/**
 * Created by Administrator on 2017/6/15.
 */

@Configuration
@EnableStateMachine
public  class MyStateMachineBuilder   extends EnumStateMachineConfigurerAdapter<States, Events> implements Builder , InitializingBean{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BeanFactory beanFactory;
    private States states;
    @Override
    public States getName() {
        logger.info("Builder getName");
        return states;
    }
    @Override
    public void  setName(States states){
        this.states=states;
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.UNPAID)
                .states(EnumSet.allOf(States.class));
    }
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)
                .event(Events.PAY)
                .and()
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_PENDING)
                .event(Events.DELAY_PAY)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
                .event(Events.RECEIVE)
                .and()
                .withExternal()
                .source(States.UNPAID).target(States.CANCEL_BUYER)
                .event(Events.CANCEL)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_PENDING).target(States.CANCEL_SELLER)
                .event(Events.CANCEL);
    }
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
                .withConfiguration()
                .listener(listener());
    }

    @Override
    public  StateMachine<States, Events> build(States states) throws Exception{
        logger.info("Builder build.");
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration().machineId("myState").autoStartup(true).beanFactory(beanFactory);

        builder.configureStates().withStates().initial(states).states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)
                .event(Events.PAY)
                .and()
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_PENDING)
                .event(Events.DELAY_PAY)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
                .event(Events.RECEIVE)
                .and()
                .withExternal()
                .source(States.UNPAID).target(States.CANCEL_BUYER)
                .event(Events.CANCEL)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_PENDING).target(States.CANCEL_SELLER)
                .event(Events.CANCEL)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_PENDING).target(States.WAITING_FOR_RECEIVE)
                .event(Events.PENDING)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
                .event(Events.MONEY);
        StateMachine stateMachine = builder.build();
        stateMachine.addStateListener(listener());
        return stateMachine;
    }
    public static StateMachine<States, Events> buildMachine(States states) throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();



        builder.configureStates().withStates().initial(states).states(EnumSet.allOf(States.class));


//       StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();
//
//        builder.configureStates()
//                .withStates()
//                .initial(states)
//                .states(EnumSet.allOf(States.class));
//
////        builder.configureTransitions()
////                .withExternal()
////                .source(States.STATE1).target(States.STATE2)
////                .event(Events.EVENT1)
////                .and()
////                .withExternal()
////                .source(States.STATE2).target(States.STATE1)
////                .event(Events.EVENT2);
        builder.configureTransitions()
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)
                .event(Events.PAY)
                .and()
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_PENDING)
                .event(Events.DELAY_PAY)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
                .event(Events.RECEIVE)
                .and()
                .withExternal()
                .source(States.UNPAID).target(States.CANCEL_BUYER)
                .event(Events.CANCEL)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_PENDING).target(States.CANCEL_SELLER)
                .event(Events.CANCEL);

        return builder.build();
    }

    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void transition(Transition<States, Events> transition) {
                if (transition.getTarget().getId() == States.UNPAID) {
                    logger.info("订单创建，待支付");
                    return;
                }
                if (transition.getSource().getId() == States.UNPAID
                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
                    logger.info("用户完成支付，待收货");
                    return;
                }
                if (transition.getSource().getId() == States.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == States.DONE) {
                    logger.info("用户已收货，订单完成");
                    return;
                }
                if(transition.getSource().getId() == States.UNPAID
                        && transition.getTarget().getId() == States.CANCEL_BUYER){
                    logger.info("买家取消");
                    return;
                }
                if(transition.getSource().getId() == States.WAITING_FOR_PENDING
                        && transition.getTarget().getId() == States.CANCEL_SELLER){
                    logger.info("卖家取消");
                    return;
                }

                if(transition.getSource().getId() == States.UNPAID
                        && transition.getTarget().getId() == States.WAITING_FOR_PENDING){
                    logger.info("货到付款");
                    return;
                }
            }
        };
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
