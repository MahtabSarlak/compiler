package AST.exp.variables;

import AST.exp.Exp;
import AST.symtab.NonStaticDscp;
import AST.symtab.SimpleDscp;
import AST.symtab.StaticDscp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

import java.util.ArrayList;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class ArrayVariable extends Variables {

    public ArrayList<Exp> arrExpression;

    public ArrayVariable(String varname,ArrayList<Exp> arrExpression) {

        this.name=varname;
        this.arrExpression = arrExpression;
    }
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        SimpleDscp dscp = getDscptor();
        if (dscp instanceof StaticDscp) {
            mv.visitFieldInsn(GETSTATIC, "$Main", getName(), getType().getDescriptor());
        } else {
            int index = ((NonStaticDscp) getDscptor()).getIndex();
            mv.visitVarInsn(ALOAD, index);
        }


        for (int i = 0; i < arrExpression.size() - 1; i++) {
            arrExpression.get(i).compile(mv, cv);
            Type t=arrExpression.get(i).getType();
            if(t!= Type.INT_TYPE&&t!= Type.CHAR_TYPE&&t!= Type.SHORT_TYPE){
                throw new RuntimeException("Index Type should be Integer");
            }
            mv.visitInsn(AALOAD);
        }
        arrExpression.get(arrExpression.size()-1).compile(mv,cv);
        mv.visitInsn(getType().getElementType().getOpcode(IALOAD));
    }

}
