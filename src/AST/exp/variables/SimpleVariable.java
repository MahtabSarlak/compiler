package AST.exp.variables;

import AST.symtab.NonStaticDscp;
import AST.symtab.SimpleDscp;
import AST.symtab.StaticDscp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static jdk.internal.org.objectweb.asm.Opcodes.GETSTATIC;

public class SimpleVariable extends Variables {

    public SimpleVariable(String svname) {

        this.name=svname;
    }


    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
       this. type = this.getType();
        SimpleDscp dscp = getDscptor();
        if (dscp instanceof StaticDscp) {
            mv.visitFieldInsn(GETSTATIC,"$Main", getName(), getType().getDescriptor());
        } else {
            int index = ((NonStaticDscp) dscp).getIndex();
            mv.visitVarInsn(getType().getOpcode(ILOAD), index);
        }
    }

}
