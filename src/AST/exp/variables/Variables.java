package AST.exp.variables;

import AST.exp.Exp;
import AST.symtab.SimpleDscp;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.Type;

public abstract class Variables extends Exp {
    public String name;

    public String getName() {
        return name;
    }

    public Type getType() throws Exception {
        return getDscptor().getType();
    }

    public SimpleDscp getDscptor() throws Exception {
        return SymbolTable.getInstance().getDescriptor(name);
    }
}
