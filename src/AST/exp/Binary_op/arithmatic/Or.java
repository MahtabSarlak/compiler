package AST.exp.Binary_op.arithmatic;

import AST.exp.Binary_op.Binaryop;
import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.IADD;
import static org.objectweb.asm.Opcodes.IOR;

public class Or extends Binaryop {

    public Or() {
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

        exp1.compile(mv,cv);
        exp2.compile(mv,cv);

        Type type = getType();

        mv.visitInsn(type.getOpcode(IOR));
    }
}
