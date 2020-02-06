package AST.symtab;

import jdk.internal.org.objectweb.asm.Type;

public abstract class NonStaticDscp extends SimpleDscp {
    int index=-1;
    public NonStaticDscp(String name, Type type, boolean isconst) {
        super(name, type, isconst);
    }

    public int getIndex() {
        return index;
    }


    public NonStaticDscp(String name, Type type, boolean isconst, int index) {
        super(name, type, isconst);
        this.index = index;
    }
}
