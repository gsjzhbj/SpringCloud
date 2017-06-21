package com.gson.proxy;

/**
 * Created by Administrator on 2017/6/20.
 */
public class ProxyObject extends AbstractObject{
    RealObject realObject=new RealObject();
    @Override
    void operate() {
        System.out.println("before");
        realObject.operate();
        System.out.println("after");
    }
}
