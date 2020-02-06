package AST.exp;

import AST.exp.UnryVariableExp.UnryExp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

public class Parantheses extends UnryExp {
    public Parantheses() {
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
    exp1.compile(mv,cv);
    type=exp1.getType();
    }
}
