package AST.exp.Loop;

import AST.block.Block;
import AST.exp.Binary_op.conditional.NotEq;
import AST.exp.Exp;
import AST.exp.constant.IntConstantExp;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class RepeatLoop extends LoopStatment {
    Exp exp1;

    public RepeatLoop(Block block, Exp exp1) {
        super(block);
        this.exp1 = exp1;
    }

    public RepeatLoop(Block block) {
        super(block);
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        SymbolTable.getInstance().addScope(SymbolTable.LOOP);
        mv.visitLabel(SymbolTable.getInstance().getLabelStart());
        NotEq notEqual = new NotEq();
        notEqual.setBinaryExp(exp1,new IntConstantExp(0));
        notEqual.compile(mv,cv);
        mv.visitJumpInsn(Opcodes.IFEQ,SymbolTable.getInstance().getLabelEnd());
        block.compile(mv,cv);
        mv.visitJumpInsn(Opcodes.GOTO,SymbolTable.getInstance().getLabelStart());
        mv.visitLabel(SymbolTable.getInstance().getLabelEnd());
        SymbolTable.getInstance().popScope();

    }
}
