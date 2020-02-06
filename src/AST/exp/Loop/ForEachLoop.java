package AST.exp.Loop;

import AST.block.Block;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

public class ForEachLoop extends LoopStatment {

    String id1;
    String id2;

    public ForEachLoop(Block block, String id1, String id2) {
        super(block);
        this.id1 = id1;
        this.id2 = id2;
    }

    public ForEachLoop(Block block) {
        super(block);
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

    }
}
