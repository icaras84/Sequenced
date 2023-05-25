package com.icaras84.core;

import java.util.function.Supplier;

public class EssWhile implements EssState {

    private EssState[] states;
    private Supplier<Boolean> conditional;
    private EssSequence internalStateManager;

    /**
     * This state is self-explanatory as WHILE a condition is true, the states in the body run
     * @param condition
     * @param states
     */
    public EssWhile(Supplier<Boolean> condition, EssState... states){
        this.conditional = condition;
        this.states = states;
        this.internalStateManager = new EssSequence();
    }

    @Override
    public void init() {
        reloadStateStack();
    }

    @Override
    public void run() {
        if (conditional.get()){
            internalStateManager.run();
            if (internalStateManager.hasNoStates()){
                reloadStateStack();
            }
        }
    }

    private void reloadStateStack(){
        this.internalStateManager.addAll(states.clone());
    }

    @Override
    public void end(){

    }

    @Override
    public boolean isFinished() {
        return !conditional.get();
    }
}