package com.gson.door;

/**
 * Created by Administrator on 2017/6/19.
 */
public class DoorClosedState extends DoorState{
    Door door;

    public DoorClosedState(Door door) {
        this.door = door;
    }

    @Override
    void touch() {
        door.setState(door.CLOSED);
        System.out.println("Door.Closed");
    }
    String status(){
        return "CLOSED";
    }
}
