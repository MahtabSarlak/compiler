package AST.exp.constant;

import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

public class BooleanConstantExp extends Constant {
    public Boolean value;

    public BooleanConstantExp(Boolean value) {
        this.value = value;
        type = Type.BOOLEAN_TYPE;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        if(value)
        IntConstantExp.storeValueAsInt(mv, 1);
        else
            IntConstantExp.storeValueAsInt(mv, 0);
    }
}
