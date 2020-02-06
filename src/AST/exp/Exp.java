package AST.exp;

import AST.Node;
import jdk.internal.org.objectweb.asm.Type;

public abstract class Exp extends Node {
    public Type type=null;

    public Exp() {

    }

    public Type getType() throws Exception {
        if(type==null)
            throw new Exception("no type setted");
        return type;
    }
}
