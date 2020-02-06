package AST.exp;

import AST.symtab.StaticDscp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

public class ExpStatment extends Statment {
    Exp exp;
    public ExpStatment(Exp exp){
        this.exp = exp;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        exp.compile(mv,cv);
        if(exp.getType()== Type.VOID_TYPE){
            if(!exp.getType().equals(Type.DOUBLE_TYPE)&&!exp.getType().equals(Type.LONG_TYPE))
                mv.visitInsn(Opcodes.POP);
            else
                mv.visitInsn(Opcodes.POP2);
        }
    }
}
