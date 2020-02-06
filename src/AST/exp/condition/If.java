package AST.exp.condition;

import AST.block.Block;
import AST.exp.Binary_op.conditional.NotEq;
import AST.exp.Exp;
import AST.exp.Statment;
import AST.exp.constant.IntConstantExp;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class If extends Statment {

    Exp exp;
    Block ifBlock;
    Block elseBlock;

    public If(Exp exp, Block ifBlock, Block elseBlock) {
        this.exp = exp;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        SymbolTable.getInstance().addScope(SymbolTable.COND_OTHER_THAN_SWITCH);
        NotEq notEqual = new NotEq ();
        notEqual.setBinaryExp(exp,new IntConstantExp(0));
        notEqual.compile(mv,cv);
        Label elseStart = new Label();
        Label elseEnd = new Label();
        mv.visitJumpInsn(Opcodes.IFEQ,elseStart);
        ifBlock.compile(mv,cv);
        mv.visitJumpInsn(Opcodes.GOTO,elseEnd);
        if(elseBlock!=null){
            SymbolTable.getInstance().popScope();
            SymbolTable.getInstance().addScope(SymbolTable.COND_OTHER_THAN_SWITCH);
            SymbolTable.getInstance().setLabelStart(elseStart);
            SymbolTable.getInstance().setLabelEnd(elseEnd);
            mv.visitLabel(elseStart);
            elseBlock.compile(mv,cv);
            mv.visitLabel(elseEnd);
        }else{
            mv.visitLabel(elseStart);
            mv.visitLabel(elseEnd);
        }
    }
}
