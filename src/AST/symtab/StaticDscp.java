package AST.symtab;

import jdk.internal.org.objectweb.asm.Type;

public abstract class StaticDscp extends SimpleDscp {

    public StaticDscp(String name, Type type, boolean isconst) {
        super(name, type, isconst);
    }
}
