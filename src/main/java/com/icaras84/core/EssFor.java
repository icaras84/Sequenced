package com.icaras84.core;

public class EssFor implements EssState {
    private int i, loopLimit;

    private EssState[] cachedStates;
    private EssSequence internalStateManager;

    /**
     * This is a specialized version of the WHILE state where it strictly operates on an iterator
     * @param loopCount
     * @param states
     */
    public EssFor(int loopCount, EssState... states){
        this.cachedStates = states;
        this.internalStateManager = new EssSequence();

        this.loopLimit = loopCount;
    }

    @Override
    public void init() {
        this.i = 0;
        reloadStateStack();
    }

    private void reloadStateStack(){
        this.internalStateManager.addAll(this.cachedStates);
    }

    @Override
    public void run() {
        if (!isFinished()){
            internalStateManager.run();
            if (internalStateManager.hasNoStates()){
                reloadStateStack();
                this.i++;
            }
        }
    }

    @Override
    public void end() {

    }

    @Override
    public boolean isFinished() {
        return this.i >= this.loopLimit;
    }

    public int getI(){
        return this.i;
    }
}
