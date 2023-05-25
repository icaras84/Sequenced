package com.icaras84.core;

public class EssAsyncGroup implements EssState {
    private EssState[] states;
    private boolean[] finishMask;

    /**
     * This state specifies what needs to be run at the same time, but this state finishes after
     * every state finishes
     * @param states
     */
    public EssAsyncGroup(EssState... states){
        this.states = states;
        this.finishMask = new boolean[this.states.length];
    }

    @Override
    public void init() {
        for (EssState s: states) {
            s.init();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < states.length; i++) {
            if (!finishMask[i]){
                states[i].run();
            }
        }

        for (int i = 0; i < states.length; i++) {
            if (states[i].isFinished()){
                states[i].end();
                finishMask[i] = true;
            }
        }
    }

    @Override
    public void end() {

    }

    @Override
    public boolean isFinished() {
        boolean output = true;
        for (boolean b: finishMask) {
            if (!b) output = false;
        }
        return output;
    }
}
