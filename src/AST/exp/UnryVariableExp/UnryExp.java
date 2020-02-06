package AST.exp.UnryVariableExp;

import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.Type;

public abstract class UnryExp extends Exp {
    public Exp exp1;
    public UnryExp(Exp exp1) {
        this.exp1 = exp1;
    }
    public UnryExp() {

    }
    public Type getType() throws Exception {
        if(type!=null)return type;
            return exp1.getType();
    }

    public UnryExp setUnryExp(Exp exp1){
        this.exp1 = exp1;
        return this;
    }


}
