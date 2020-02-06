package AST.exp.Binary_op.arithmatic;

import AST.exp.Binary_op.Binaryop;
import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;
import static jdk.internal.org.objectweb.asm.Opcodes.IREM;

public class Mod extends Binaryop {

    public Mod() {

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

        exp1.compile(mv,cv);
        exp2.compile(mv,cv);

        Type type = getType();

        mv.visitInsn(type.getOpcode(IREM));
    }
}
