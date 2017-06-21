package com.gson.door;

/**
 * Created by gaoshja on 2017/6/19.
 */
public abstract class DoorState {
    static final String CLOSED="CLOSED";
    static final String OPENED="OPENED";
    abstract void touch();
    void complete(){};
    void timeout(){};
    String status(){return "";};
}
