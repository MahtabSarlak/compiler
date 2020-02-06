package AST.exp.constant;

import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

import static jdk.internal.org.objectweb.asm.Opcodes.LCONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.LCONST_1;

public class LongConstantExp extends Constant {
    public Long value;

    public LongConstantExp(Long value) {
        this.value = value;
        type = Type.LONG_TYPE;
    }
    public Long getValue() {
        return value;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        if (value == 0)
            mv.visitInsn(LCONST_0);
        else if (value == 1)
            mv.visitInsn(LCONST_1);
        else
            mv.visitLdcInsn(value);
    }
}
