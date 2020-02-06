package AST.exp.Binary_op;

import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

public abstract class Binaryop extends Exp {
    public Exp exp1,exp2;

    public Binaryop(Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public Binaryop() {

    }

    public Type getType() throws Exception {
        if(type!=null)return type;
        if(exp1.type!=exp2.type)
        {
            System.out.println(exp1.toString());
            System.out.println(exp1.type);
            System.out.println(exp2.toString());
            System.out.println(exp2.type);
            throw new Exception("Type not matched");

        }

            return type =exp1.getType();

    }

    public Binaryop setBinaryExp(Exp exp1, Exp exp2){
        this.exp1=exp1;
        this.exp2=exp2;
        return this;
    }


    public void notIntCompare(MethodVisitor mv, Type type) {
        if (type == Type.DOUBLE_TYPE) {
            mv.visitInsn(Opcodes.DCMPG);
        } else if (type == Type.LONG_TYPE) {
            mv.visitInsn(Opcodes.LCMP);
        }
    }
}
