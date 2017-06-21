package com.gson;

import com.gson.order.OrderStates;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/6/19.
 */

public class OrderStatesTest {
    private Logger logger = LoggerFactory.getLogger("state");
    @Test
    public void testUnPaid(){
        logger.info("{} {} {}", OrderStates.UNPAID,OrderStates.UNPAID.getDesc(),OrderStates.UNPAID.getValue());
        OrderStates state=OrderStates.UNPAID;
        switch (state){
            case UNPAID:
                System.out.println(OrderStates.UNPAID);
                break;
        }
    }
}
