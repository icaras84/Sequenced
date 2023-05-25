package com.icaras84.core;

public class EssWaitFor<T extends Enum<?>> implements EssState {
    private final EssFlag<T> monitoredFlag;
    private final T targetState;
    private boolean exit;

    /**
     * This state is a special command for the state machine to wait
     * until a certain flag pops-up
     * @param flag
     * @param target
     */
    public EssWaitFor(EssFlag<T> flag, T target){
        this.monitoredFlag = flag;
        this.targetState = target;
    }

    @Override
    public void init() {}

    @Override
    public void run() {
        this.exit = test();
    }

    private boolean test(){
        return monitoredFlag.getCurrentState().equals(targetState);
    }

    @Override
    public void end() {}

    @Override
    public boolean isFinished() {
        return this.exit;
    }
}
