package AST.exp.Binary_op.conditional;

import AST.exp.Binary_op.Binaryop;
import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.Type;
public class Greater extends Binaryop {
    public Greater()
    {

    }
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

        exp1.compile(mv, cv);
        exp2.compile(mv, cv);

        Type type = getType();
        int op;
        if(type!= Type.INT_TYPE) {
            if (type == Type.DOUBLE_TYPE || type == Type.FLOAT_TYPE) {
                mv.visitInsn(Opcodes.DCMPL);
            } else if (type == Type.LONG_TYPE) {
                mv.visitInsn(Opcodes.LCMP);
            }

             op = Opcodes.IFLE;
        }else {
            op=Opcodes.IF_ICMPLE;
        }
            Label l1=new Label();
            Label l2=new Label();

            mv.visitJumpInsn(op,l1);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitJumpInsn(Opcodes.GOTO,l2);
            mv.visitLabel(l1);
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitLabel(l2);

    }
}
