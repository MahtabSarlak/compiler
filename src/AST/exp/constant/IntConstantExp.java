package AST.exp.constant;

import AST.exp.Exp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

import static jdk.internal.org.objectweb.asm.Opcodes.BIPUSH;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_1;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_2;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_3;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_4;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_5;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_M1;
import static jdk.internal.org.objectweb.asm.Opcodes.SIPUSH;
import static jdk.internal.org.objectweb.asm.Opcodes.*;

import    jdk.internal.org.objectweb.asm.Opcodes.*;

public class IntConstantExp extends Constant {

    public int value;

    public IntConstantExp(Integer value) {
        System.out.println(value);
        this.type = Type.INT_TYPE;
        this.value = value;
    }


    public Object getValue() {return value;}

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        storeValueAsInt(mv,value);
    }

    //todo ...
    public static void storeValueAsInt(MethodVisitor mv, int value) {

        if (value >= -1 && value <= 5) {
            switch (value) {
                case -1:
                    mv.visitInsn(ICONST_M1);
                    break;
                case 0:
                    mv.visitInsn(ICONST_0);
                    break;
                case 1:
                    mv.visitInsn(ICONST_1);
                    break;
                case 2:
                    mv.visitInsn(ICONST_2);
                    break;
                case 3:
                    mv.visitInsn(ICONST_3);
                    break;
                case 4:
                    mv.visitInsn(ICONST_4);
                    break;
                case 5:
                    mv.visitInsn(ICONST_5);
                    break;
            }
        } else if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
            mv.visitIntInsn(BIPUSH, value);
        } else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE) {
            mv.visitIntInsn(SIPUSH, value);
        } else {
            mv.visitLdcInsn(value);
        }
    }

}
