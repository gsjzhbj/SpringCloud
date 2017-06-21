package com.gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class BuildFactory implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private List<Builder> builders;

    private Map<States, Builder> statesStateMachineMap = new ConcurrentHashMap<>();

    public StateMachine run(States state, Events event) throws Exception{
        logger.info("BuildFactory run.");
        Builder builder = statesStateMachineMap.get(state);

        StateMachine<States, Events> stateMachine = builder.build(state);
        stateMachine.sendEvent(event);
        return stateMachine;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("BuildFactory");
        statesStateMachineMap = builders.stream().collect(Collectors.toMap(Builder::getName, Function.identity()));
    }

    private  States initStates;
    public void setInitStates(States states){
        this.initStates=states;
    }
    public States getInitStates(){
        return initStates;
    }
}
