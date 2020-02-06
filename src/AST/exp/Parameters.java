package AST.exp;

import AST.Node;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class Parameters extends Node {
    ArrayList<Exp>exps=new ArrayList<>();
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

    }

    public Parameters(ArrayList<Exp> exps) {
        for(Exp e:exps)
        {
            this.exps.add(e);
        }
    }


}
