package AST.symtab;

import jdk.internal.org.objectweb.asm.Type;

public class SimpleNonStaticDscp extends NonStaticDscp{

    public SimpleNonStaticDscp(String name, Type type, boolean isconst, int index) {
        super(name, type, isconst, index);
    }
    public int getIndex() {
        return index;
    }
}
