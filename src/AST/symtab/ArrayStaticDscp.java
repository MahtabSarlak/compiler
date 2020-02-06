package AST.symtab;

import jdk.internal.org.objectweb.asm.Type;

public class ArrayStaticDscp extends StaticDscp{
    int arrSize;

    public int getArrSize() {
        return arrSize;
    }

    public ArrayStaticDscp(String name, Type type, boolean isconst, int arrSize) {
        super(name, type, isconst);
        this.arrSize = arrSize;
    }
}
