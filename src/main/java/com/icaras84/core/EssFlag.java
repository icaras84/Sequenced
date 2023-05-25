package com.icaras84.core;

public class EssFlag<T extends Enum<?>> {

    public enum Basic{
        ON,
        OFF
    }

    private T currentState;

    public EssFlag(){
        this(null);
    }

    public EssFlag(T initialState){
        this.currentState = initialState;
    }

    public T getCurrentState(){
        return this.currentState;
    }

    public EssFlag setCurrentState(T nState){
        this.currentState = nState;
        return this;
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof EssFlag){
            EssFlag oFlag = (EssFlag) other;
            return oFlag.getCurrentState().equals(this.currentState);
        } else {
            return false;
        }
    }
}
