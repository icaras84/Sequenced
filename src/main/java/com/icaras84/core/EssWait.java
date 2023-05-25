package com.icaras84.core;

public class EssWait implements EssState {
    private long durationMS;

    private long lastTime = 0L, currTime = 0L, deltaTime = 0L, projectedTime = 0L;

    /**
     * This state is a special command for the state machine to wait
     * (non-blocking, so the main thread can still proceed)
     * @param ms
     */
    public EssWait(long ms){
        this.durationMS = ms;
    }

    public EssWait(double ms){
        this((long) ms);
    }

    @Override
    public void init() {
        lastTime = System.currentTimeMillis();
        currTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        currTime = System.currentTimeMillis();
        deltaTime = currTime - lastTime;
        projectedTime = currTime + deltaTime;
        lastTime = currTime;
    }

    @Override
    public void end() {

    }

    @Override
    public boolean isFinished() {
        return currTime + (0.02 * deltaTime) >= durationMS;
    }

    public long getDeltaTimeMS(){
        return this.deltaTime;
    }

    public long getProjectedTimeMS(){
        return this.projectedTime;
    }
}
