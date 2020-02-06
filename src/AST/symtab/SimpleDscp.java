package AST.symtab;

import jdk.internal.org.objectweb.asm.*;

public abstract class SimpleDscp {

    String name;

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    Type type;
    boolean isconst;

    public SimpleDscp(String name, Type type, boolean isconst) {
        this.name = name;
        this.type = type;
        this.isconst = isconst;
    }

    public boolean isIsconst() {
        return isconst;
    }

    public SimpleDscp(String name, Type type ) {
        this.type = type;
        this.name = name;
    }

}
