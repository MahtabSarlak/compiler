package AST.exp.constant;

import AST.exp.Exp;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

public class Sizeof extends Constant {

    String par;
    int value;

    public Sizeof(String par)  {
        this.par = par;
        int size = 0;
            switch (par) {
                case "int":
                    size = Integer.SIZE;
                    break;
                case "long":
                    size = Long.SIZE;
                    break;
                case "char":
                    size = Character.SIZE;
                    break;
                case "bool":
                    size = 1;
                    break;
                case "double":
                    size = Double.SIZE;
                    break;
                case "float":
                    size = Float.SIZE;
                    break;
                case "string":
                    size = Integer.SIZE;
                    break;
                default:
                    throw new IllegalArgumentException("Type is not Valid");

            }
        value=size;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        IntConstantExp.storeValueAsInt(mv,value);
    }
}
