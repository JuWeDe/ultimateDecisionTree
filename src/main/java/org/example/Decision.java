package org.example;
import java.util.Map;

public abstract class Decision<T> {
    private Decision<T> trueBranch;
    private Decision<T> falseBranch;
    public void setTrueBranch(Decision<T> trueBranch) {
        this.trueBranch = trueBranch;
    }
    public void setFalseBranch(Decision<T> falseBranch) {
        this.falseBranch = falseBranch;
    }
    public abstract T evaluate(Map<T, Boolean> questions);
    public Decision<T> getTrueBranch() {
        return trueBranch;
    }
    public Decision<T> getFalseBranch() {
        return falseBranch;
    }

}
