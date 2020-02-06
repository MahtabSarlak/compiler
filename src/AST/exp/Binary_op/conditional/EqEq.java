package AST.exp.Binary_op.conditional;

import AST.exp.Binary_op.Binaryop;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

import static jdk.internal.org.objectweb.asm.Opcodes.IFNE;
import static jdk.internal.org.objectweb.asm.Opcodes.IF_ICMPNE;
import static jdk.internal.org.objectweb.asm.Opcodes.GOTO;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_1;


public class EqEq extends Binaryop {
    public EqEq()
    {

    }
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        exp1.compile(mv, cv);
        exp2.compile(mv, cv);

        Type type = getType();
        // TODO: 30/06/2018 Exception for Record



        int opcode;
        if (type != Type.INT_TYPE) {
            notIntCompare(mv, type);
            opcode = IFNE;
        } else {
            opcode =  IF_ICMPNE;
        }

        Label l1 = new Label();
        Label l2 = new Label();
        mv.visitJumpInsn(opcode, l1);
        mv.visitInsn(ICONST_1);
        mv.visitJumpInsn(GOTO, l2);
        mv.visitLabel(l1);
        mv.visitInsn(ICONST_0);
        mv.visitLabel(l2);
    }

}
