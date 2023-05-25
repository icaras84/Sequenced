package com.icaras84.core;

/**
 * This interface can be implemented to give a "command" that is dedicated to a single job.
 * It also allows the ability to put those states in a "State Machine" ({@code State.Sequence})
 * The purpose of this interface extending Runnable is to allow the freedom of throwing this object
 * on another thread if necessary, allowing for true asynchronous actions
 */
public interface EssState extends Runnable{

    /**
     * This method runs once on the instance that this state is reached
     */
    void init();

    /**
     * This method runs as periodically as soon as this state is reached
     */
    void run();

    /**
     * This method is run once on the instant that this state finishes
     */
    void end();

    /**
     * This method dictates if this state is finished or not
     * @return
     */
    boolean isFinished();
}