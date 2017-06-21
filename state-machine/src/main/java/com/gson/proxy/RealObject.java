package com.gson.proxy;

/**
 * Created by Administrator on 2017/6/20.
 */
public class RealObject extends AbstractObject{
    @Override
    void operate() {
        System.out.println("real operate.");
    }
}
