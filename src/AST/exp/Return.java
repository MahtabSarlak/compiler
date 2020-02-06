package AST.exp;

import AST.decleration.fun_dcl.FuncDcleration;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import help.HelperFunctions;

import java.util.HashMap;

public class Return extends Statment {
    HashMap scope ;
    Exp exp1=null;

    public Return(Exp exp1) {
        this.exp1 = exp1;
    }
    public Return(){
        exp1=null;
    }
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        scope = SymbolTable.getInstance().getLastFrame();
        FuncDcleration f = (FuncDcleration)SymbolTable.getInstance().getLastFunction();
        for(Return r : f.returns){
            if(r.scope==scope){
                throw new RuntimeException("more than one return in one scope");
            }
        }
        f.returns.add(this);
        if(exp1!=null){
            exp1.compile(mv,cv);
            if(f.getType().equals(Type.VOID_TYPE)){
                throw new RuntimeException("error have return in void");
            }
            if(!f.getType().equals(exp1.getType())){
                throw new RuntimeException("return type mismatch");
            }
            HelperFunctions.cast(exp1.getType(),f.getType(),mv,cv);
            mv.visitInsn(exp1.getType().getOpcode(Opcodes.IRETURN));
        }else {
            if(!f.getType().equals(Type.VOID_TYPE)){
                throw new RuntimeException("return something");
            }
            mv.visitInsn(Opcodes.RETURN);
        }
    }
}
