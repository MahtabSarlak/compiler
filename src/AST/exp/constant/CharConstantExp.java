package AST.exp.constant;

import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

public class CharConstantExp extends Constant {
    public Character value;

    public CharConstantExp(Character value) {
        this.value = value;
        type = Type.CHAR_TYPE;
    }

    public Character getValue() {
        return value;
    }


    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        IntConstantExp.storeValueAsInt(mv,(int)value);
    }
}
