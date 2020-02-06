package AST;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

public abstract class Node {
    abstract public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception;

}
