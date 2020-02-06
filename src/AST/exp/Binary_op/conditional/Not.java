package AST.exp.Binary_op.conditional;

import AST.exp.Binary_op.Binaryop;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;


public class Not extends Binaryop {
    public Not()
    {

    }
    //todo ....
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

        throw new Exception("Todo in future OR not supported");
    }
}
