package com.gson;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class Application implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Autowired
    private BuildFactory buildFactory;

    @Autowired
    private MyStateMachineBuilder myStateMachineBuilder;
    @Override
    public void run(String... args) throws Exception {
//        stateMachine.start();
//        stateMachine.sendEvent(Events.DELAY_PAY);
//        stateMachine.sendEvent(Events.PAY);
//        logger.info(stateMachine.toString());

        stateMachine=myStateMachineBuilder.build(States.UNPAID);
        stateMachine.sendEvent(Events.CANCEL);

        stateMachine=myStateMachineBuilder.build(States.WAITING_FOR_RECEIVE);
        stateMachine.sendEvent(Events.RECEIVE);

//        buildFactory.setInitStates(States.WAITING_FOR_PENDING);
//        stateMachine=buildFactory.run(States.WAITING_FOR_PENDING, Events.PENDING);
//        logger.info(stateMachine.toString());
//        stateMachine.sendEvent(Events.MONEY);
//        //buildFactory.run(States.CANCEL_SELLER, Events.CANCEL);
    }

}
