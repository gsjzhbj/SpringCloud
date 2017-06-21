package com.gson.door;

/**
 * Created by Administrator on 2017/6/19.
 */
public class TestDoor {public static void main(String[] args){
    Door door=new Door();

    //1. 初始状态
    System.out.println(door.status());

    //2. 转移到Opened状态
    door.setState(new DoorOpendState(door));
    door.touch();
    System.out.println(door.status());

    //3. 回到Closed状态
    door.setState(new DoorClosedState(door));
    door.touch();
    System.out.println(door.status());
}

}
