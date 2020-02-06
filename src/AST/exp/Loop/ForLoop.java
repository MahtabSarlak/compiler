package AST.exp.Loop;

import AST.block.Block;
import AST.exp.Assignment.Assignment;
import AST.exp.Binary_op.conditional.NotEq;
import AST.exp.Exp;
import AST.exp.constant.IntConstantExp;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class ForLoop extends LoopStatment {
    Assignment assign1;
    //todo if third part is an assignment ????
  //  Assignment assign2;
    Exp exp1;
    Exp exp2;

    public ForLoop(Block block, Assignment assign1, Exp exp1, Exp exp2) {
        super(block);
        this.assign1 = assign1;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public ForLoop(Block block) {
        super(block);
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        SymbolTable.getInstance().addScope(SymbolTable.LOOP);
        if(assign1 !=null){
            assign1.compile(mv,cv);
        }
        mv.visitLabel(SymbolTable.getInstance().getLabelStart());
        exp1.compile(mv,cv);
        NotEq notEq = new NotEq();
        notEq.setBinaryExp(exp1,new IntConstantExp(0));
        mv.visitJumpInsn(Opcodes.IFEQ,SymbolTable.getInstance().getLabelEnd());
        if(exp2!=null){
            exp2.compile(mv,cv);
            mv.visitInsn(Opcodes.POP);
        }
        block.compile(mv,cv);
        mv.visitJumpInsn(Opcodes.GOTO,SymbolTable.getInstance().getLabelStart());
        mv.visitLabel(SymbolTable.getInstance().getLabelEnd());
        SymbolTable.getInstance().popScope();

    }
}
