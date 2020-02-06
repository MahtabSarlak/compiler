package AST.exp.Binary_op.conditional;

import AST.exp.Binary_op.Binaryop;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.*;

public class and extends Binaryop {
    public and()
    {

    }
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

        Type type = getType();
        if (type != Type.INT_TYPE)
            throw new Exception("Only Int Types Can Be Operands Of Conditional And");

        Label L1 = new Label();
        Label L2 = new Label();
        exp1.compile(mv, cv);
        mv.visitJumpInsn( IFEQ , L1);
        exp2.compile(mv, cv);
        mv.visitJumpInsn( IFEQ , L1);
        mv.visitInsn( ICONST_1 );
        mv.visitJumpInsn(GOTO, L2);
        mv.visitLabel(L1);
        mv.visitInsn( ICONST_0);
        mv.visitLabel(L2);

    }
}
