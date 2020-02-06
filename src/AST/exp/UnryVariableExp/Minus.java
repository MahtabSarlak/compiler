package AST.exp.UnryVariableExp;

import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.INEG;

public class Minus extends UnryExp {
    public Minus() {
    }

    public Minus(Exp exp1) {
        super(exp1);
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        exp1.compile(mv,cv);
        type=exp1.getType();
        mv.visitInsn(exp1.getType().getOpcode(INEG));
    }
}
