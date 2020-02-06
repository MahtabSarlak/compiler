package AST.exp.Binary_op.arithmatic;

import AST.exp.Binary_op.Binaryop;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

import javax.swing.plaf.basic.BasicIconFactory;

import static jdk.internal.org.objectweb.asm.Opcodes.IADD;
import static jdk.internal.org.objectweb.asm.Opcodes.IXOR;

public class Xor  extends Binaryop {
    public Xor() {
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

        exp1.compile(mv,cv);
        exp2.compile(mv,cv);

        Type type = getType();

        mv.visitInsn(type.getOpcode(IXOR));
    }

}
