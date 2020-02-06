package AST.symtab;

import jdk.internal.org.objectweb.asm.Type;

public class ArrayNonStaticDscp extends NonStaticDscp {
    int arrSize;

    public int getArrSize() {
        return arrSize;
    }

    public ArrayNonStaticDscp(String name, Type type, boolean isconst, int index, int arrSize) {
        super(name, type, isconst, index);
        this.arrSize = arrSize;
    }
    public int getIndex() {
        return index;
    }
}
