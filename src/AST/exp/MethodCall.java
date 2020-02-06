package AST.exp;

import AST.decleration.fun_dcl.Extern_dcl;
import AST.decleration.fun_dcl.Func_extern;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import help.DefinedValues;

import java.util.ArrayList;

public class MethodCall extends Exp {
    String id;
    ArrayList<Exp> parameters=new ArrayList<>();

    Func_extern func;
    public MethodCall(String id, ArrayList<Exp> parameters) {
        this.id = id;
        this.parameters = parameters;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        Label start = new Label();
        Label middle = new Label();
        Label end = new Label();
        mv.visitJumpInsn(Opcodes.GOTO,middle);
        mv.visitLabel(start);
        for (Exp exp : parameters) {
            exp.compile(mv, cv);
        }
        mv.visitJumpInsn(Opcodes.GOTO,end);
        Type[] typesInComped = new Type[parameters.size()];
        for (int i = 0; i < parameters.size(); i++) {
            typesInComped[i] = parameters.get(i).getType();
        }

        this.func = SymbolTable.getInstance().getFunction(id, typesInComped);
        this.type = func.getType();

            if(parameters.size()!=func.inputs.length){

                throw new RuntimeException("error in func parameter");
            }

            mv.visitLabel(middle);
            mv.visitJumpInsn(Opcodes.GOTO,start);
            mv.visitLabel(end);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, DefinedValues.nameClass, func.getName(), func.getSignature(), false);
        }

}
