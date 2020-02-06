package AST.symtab;

import jdk.internal.org.objectweb.asm.Type;

public class SimpleStaticDscp extends StaticDscp {
    public SimpleStaticDscp(String name, Type type, boolean isconst) {
        super(name, type, isconst);
    }
}
