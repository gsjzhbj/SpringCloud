package com.gson.door;

import java.util.Observable;

/**
 * Created by gaoshja on 2017/6/19.
 */
public class Door extends Observable {
    public final DoorState CLOSED = new DoorClosedState(this);
    public final DoorState OPENED = new DoorOpendState(this);

    private DoorState state = CLOSED;

    public void touch() {
        switch (state.status()){
            case DoorState.OPENED:
                OPENED.touch();
                break;
            case DoorState.CLOSED:
                CLOSED.touch();
                break;
        }
//        setState(state);
//        state.touch();
    }

    public void complete() {
        state.complete();
    }

    public void timeout() {
        state.timeout();
    }

    public String status() {
        return state.status();
    }

    protected void setState(DoorState state) {
        this.state = state;
        setChanged();
        notifyObservers();
    }
}
