package com.icaras84.core;

import java.util.function.Supplier;

public class EssIf implements EssState {

    private EssState t, f;
    private Supplier<Boolean> conditional;

    /**
     * This state is self-explanatory as IF a condition is true, the state in the truth body runs,
     * but while false the state in the false body runs
     * @param condition
     * @param conditionTrue
     * @param conditionFalse
     */
    public EssIf(Supplier<Boolean> condition, EssState conditionTrue, EssState conditionFalse){
        this.t = conditionTrue;
        this.f = conditionFalse;
        this.conditional = condition;
    }

    @Override
    public void init() {
        if (conditional.get()){
            t.init();
        } else {
            f.init();
        }
    }

    @Override
    public void run() {
        if (conditional.get()){
            t.run();
        } else {
            f.run();
        }
    }

    @Override
    public void end() {
        if (conditional.get()){
            t.end();
        } else {
            f.end();
        }
    }

    @Override
    public boolean isFinished() {
        if (conditional.get()){
            return t.isFinished();
        } else {
            return f.isFinished();
        }
    }
}
