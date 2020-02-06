package AST.decleration;

import AST.TempNode;
import AST.exp.Exp;
import AST.symtab.SimpleDscp;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

public abstract class Var_dcl extends TempNode {
    public String id;
    public boolean isconst;
    public Exp exp=null ;
    public Type type=null ;


    public Exp getExp() {
        return exp;
    }

    public String getName() {
        return id;
    }

    public Type getType() throws Exception {
        if(type == null)
            throw new RuntimeException("no type sett");

        return type;
    }

    public SimpleDscp getDscptor() throws Exception {
        return SymbolTable.getInstance().getDescriptor(id);
    }
    public boolean isconst(){
        return isconst;
    }

    public abstract void addFieldInByteCode(ClassVisitor cv, boolean isstatic) throws Exception;
    public abstract void FindType(MethodVisitor mv, ClassVisitor cv) throws Exception;
}
