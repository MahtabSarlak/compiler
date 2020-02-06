package AST.exp.UnryVariableExp;

import AST.exp.Exp;
import AST.exp.variables.SimpleVariable;
import AST.exp.variables.Variables;
import AST.symtab.NonStaticDscp;
import AST.symtab.SimpleDscp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.INEG;

public class MinusMinuspos extends UnryExp {
    public MinusMinuspos(Exp exp1) {
        super(exp1);
    }

    public MinusMinuspos() {
    }
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        if (!(exp1 instanceof Variables) || (type != Type.INT_TYPE && type != Type.DOUBLE_TYPE && type != Type.LONG_TYPE && type != Type.CHAR_TYPE))
            throw new IllegalArgumentException();
        Variables var = (Variables) exp1;
        type = var.getType();
        if (exp1 instanceof SimpleVariable) {
            SimpleDscp dscp = var.getDscptor();
            if (dscp instanceof NonStaticDscp) {
                int index = ((NonStaticDscp) dscp).getIndex();
                if (type == Type.INT_TYPE || type == Type.CHAR_TYPE) {
                    var.compile(mv,cv);
                    mv.visitIincInsn(index, -1);
                }
                else {
                    var.compile(mv,cv);//Postfix

                    var.compile(mv,cv);

                    if (type == Type.DOUBLE_TYPE) {
                        mv.visitInsn(DCONST_1);
                    }
                    else {
                        mv.visitInsn(LCONST_1);
                    }

                    mv.visitInsn(type.getOpcode(ISUB));
                    mv.visitVarInsn(type.getOpcode(ISTORE), index);
                }

            } else {
                throw new RuntimeException();
            }
        }
    }
}
