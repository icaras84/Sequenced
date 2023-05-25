package com.icaras84.core;

import java.util.LinkedList;

public class EssSequence implements EssState {
    private LinkedList<EssState> stateQueue;
    private long startTime;

    private long loopStartTime, loopEndTime;

    private boolean sInit = false;
    private EssState runningState = null;

    private boolean guaranteeRunOnce = false;

    private static final double MS2SEC = 1e-3d;

    /**
     * This state is akin to a "State Machine" where it details a specified sequence of states
     * it needs to run and has logic for handling that
     */
    public EssSequence(){
        stateQueue = new LinkedList<>();
    }

    /**
     * This state is akin to a "State Machine" where it details a specified sequence of states
     * it needs to run and has logic for handling that (This constructor copies another sequence into a new object)
     * @param stateSequence
     */
    public EssSequence(EssSequence stateSequence){
        this.stateQueue = (LinkedList<EssState>) stateSequence.stateQueue.clone();
    }

    public EssSequence toggleGuaranteeStateRunsOnce(boolean nSetting){
        this.guaranteeRunOnce = nSetting;
        return this;
    }

    public EssSequence add(EssState state){
        stateQueue.add(state);
        return this;
    }

    public EssSequence addAll(EssState... states){
        for (EssState s: states) {
            add(s);
        }
        return this;
    }

    public EssSequence addSequence(EssSequence sequence){
        addAll(sequence.getSequence());
        return this;
    }

    public EssState[] getSequence(){
        EssState[] states = stateQueue.toArray(new EssState[0]);
        return states;
    }

    public EssSequence addToNext(EssState state){
        stateQueue.add(1, state);
        return this;
    }

    @Override
    public void init() {

    }

    public void run(){
        if (!stateQueue.isEmpty()) { //check if the stack is filled
            if (!sInit) { //test if the current state has initialized (default is false)
                startTime = System.currentTimeMillis();

                runningState = stateQueue.peek();
                runningState.init();
                sInit = true; //flag true after running initialization to prevent another init call
            }

            loopStartTime = System.currentTimeMillis();
            if (guaranteeRunOnce || !runningState.isFinished()) runningState.run();
            loopEndTime = System.currentTimeMillis();

            if (runningState.isFinished()) { //check if current state is finished
                runningState.end(); //call end() of current state
                stateQueue.pop(); //dispose of the state
                sInit = false; //flag that the next state needs to initialize
            }
        } else {
            startTime = System.currentTimeMillis();
        }
    }

    @Override
    public void end() {

    }

    @Override
    public boolean isFinished() {
        return isEmpty();
    }

    public boolean hasNoStates(){
        return stateQueue.isEmpty();
    }

    public boolean isEmpty(){
        return stateQueue.isEmpty();
    }

    public double getCurrentStateElapsedTimeMS(){
        return System.currentTimeMillis() - startTime;
    }

    public double getCurrentStateElapsedTimeSEC(){
        return getCurrentStateElapsedTimeMS() * MS2SEC;
    }

    public double getDeltaTimeMS(){
        return loopEndTime - loopStartTime;
    }

    public double getStartTimeMS(){
        return startTime;
    }

    public double getStartTimeSEC(){
        return startTime * MS2SEC;
    }
}
