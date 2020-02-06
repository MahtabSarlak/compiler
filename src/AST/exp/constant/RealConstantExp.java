package AST.exp.constant;

import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

import static jdk.internal.org.objectweb.asm.Opcodes.DCONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.DCONST_1;

public class RealConstantExp extends Constant {
    public Double value;

    public RealConstantExp(Double value) {
        this.value = value;
        type = Type.DOUBLE_TYPE;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        if (value == 0)
            mv.visitInsn(DCONST_0);
        else if (value == 1)
            mv.visitInsn(DCONST_1);
        else
            mv.visitLdcInsn(value);
    }
}
