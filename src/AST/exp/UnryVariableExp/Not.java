package AST.exp.UnryVariableExp;

import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_M1;
import static jdk.internal.org.objectweb.asm.Opcodes.IXOR;

public class Not extends UnryExp {
    public Not() {
    }

    public Not(Exp exp1) {
        super(exp1);
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        if (type != Type.INT_TYPE)
            throw new RuntimeException("Invalid Type");

        exp1.compile(mv, cv);
        type = getType();
        mv.visitInsn(ICONST_M1);
        mv.visitInsn(type.getOpcode(IXOR));
    }
}
