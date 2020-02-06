package AST.exp.constant;

import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

public class StringConstantExp extends Constant {
    public String value;

    public StringConstantExp(String value) {
        type = Type.getType(String.class);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        mv.visitLdcInsn(value);
    }

}
