package com.icaras84.core;

import java.util.concurrent.ForkJoinPool;

public class EssParallelAsyncGroup implements EssState {

    private volatile EssSequence internalStateManager;
    private volatile EssState[] states;

    /**
     * This state is a special type of {@code AsyncGroup} state where it puts every state on an
     * async group and tells it to run in a separate thread, finishing immediately
     * @param states
     */
    public EssParallelAsyncGroup(EssState... states){
        this.states = states;
        this.internalStateManager = new EssSequence();
        this.internalStateManager.addAll(states);
    }

    @Override
    public void init() {
        ForkJoinPool.commonPool().submit(
                () -> {
                    while (!internalStateManager.hasNoStates()) {
                        internalStateManager.run();
                    }

                    try{
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public void run() {

    }

    @Override
    public void end() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
