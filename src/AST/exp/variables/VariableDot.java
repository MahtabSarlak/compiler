package AST.exp.variables;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

public class VariableDot extends Variables {
    Variables v1;
    String id;
    public VariableDot(Variables v1, String v2){
        name = v1.getName() + id;
        this.v1 = v1;
        this.id = v2;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

    }
}
