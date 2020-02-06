package AST.exp.Assignment;

import AST.exp.Exp;
import AST.exp.variables.SimpleVariable;
import AST.exp.variables.Variables;
import AST.symtab.NonStaticDscp;
import AST.symtab.SimpleDscp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import static jdk.internal.org.objectweb.asm.Opcodes.ISTORE;
import help.DefinedValues;
import help.HelperFunctions;


public class Assignment extends Assign {

    public Assignment(Exp exp1, Variables var1) {
        super(var1,exp1);
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        this.type = var.getType();
        if (!(var instanceof SimpleVariable))
            throw new RuntimeException();
        SimpleDscp dscp = var.getDscptor();
        exp1.compile(mv,cv);
        if (var.getType() != exp1.getType())
            HelperFunctions.cast(exp1.getType(), var.getType(), mv, cv);
        if(dscp instanceof NonStaticDscp) {
            int index = ((NonStaticDscp) dscp).getIndex();
            if(var.getType().equals(Type.DOUBLE_TYPE)){
                mv.visitVarInsn(var.getType().getOpcode(ISTORE), index);
                var.compile(mv,cv);
            }else{
                mv.visitVarInsn(var.getType().getOpcode(ISTORE), index);
                var.compile(mv,cv);
            }
        }else{
            mv.visitFieldInsn(Opcodes.PUTSTATIC, DefinedValues.nameClass, dscp.getName(), dscp.getType().toString());
        }

    }
}
