package AST.block;

import AST.Node;
import AST.TempNode;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class Block extends Node {

    ArrayList<TempNode> blockArguments=new ArrayList<>();

    public Block(ArrayList<TempNode> blockArguments) {
        this.blockArguments = blockArguments;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

        try {
            for (TempNode t : blockArguments) {
                t.compile(mv, cv);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
