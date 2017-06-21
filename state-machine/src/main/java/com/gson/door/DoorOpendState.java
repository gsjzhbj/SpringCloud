package com.gson.door;

/**
 * Created by Administrator on 2017/6/19.
 */
public class DoorOpendState extends DoorState {
    Door door;

    public DoorOpendState(Door door) {
        this.door = door;
    }

    @Override
    void touch() {
        door.setState(door.OPENED);
        System.out.println("Door.Open");

    }

    String status(){
        return "OPENED";
    }
}
