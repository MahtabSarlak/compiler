package AST.exp.Assignment;

import AST.exp.Exp;
import AST.exp.variables.SimpleVariable;
import AST.exp.variables.Variables;
import AST.symtab.NonStaticDscp;
import AST.symtab.SimpleDscp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import help.DefinedValues;
import help.HelperFunctions;

import static org.objectweb.asm.Opcodes.ISTORE;

public class MultAssignment extends Assign {
    public MultAssignment(Variables var, Exp exp1) {
        super(var, exp1);
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

        this.type = var.getType();
        if (!(var instanceof SimpleVariable))
            throw new RuntimeException();
        SimpleDscp dscp = var.getDscptor();
        var.compile(mv,cv);
        exp1.compile(mv,cv);
        if (var.getType() != exp1.getType())
            HelperFunctions.cast(exp1.getType(), var.getType(), mv, cv);
        mv.visitInsn(var.getType().getOpcode(Opcodes.IMUL));
        if(dscp instanceof NonStaticDscp) {
            int index = ((NonStaticDscp) dscp).getIndex();
            mv.visitVarInsn(var.getType().getOpcode(ISTORE), index);
            var.compile(mv,cv);
        }else{
            mv.visitFieldInsn(Opcodes.PUTSTATIC, DefinedValues.nameClass, dscp.getName(), dscp.getType().toString());
        }
    }
}
