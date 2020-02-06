package AST.exp.condition;

import AST.block.Block;
import AST.exp.Statment;
import AST.exp.constant.IntConstantExp;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class CasePart extends Statment {
    Block block;
    IntConstantExp int_const;

    Label labelStartCase;
    Label jump;
    Label start;

    public CasePart(Block block, IntConstantExp int_const) {
        this.block = block;
        this.int_const = int_const;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        labelStartCase = new Label();
        mv.visitLabel(labelStartCase);
        SymbolTable.getInstance().addScope(SymbolTable.SWITCH);
        SymbolTable.getInstance().setLabelLast(jump);
        SymbolTable.getInstance().setLabelFirst(start);
        block.compile(mv,cv);
        SymbolTable.getInstance().popScope();
        mv.visitJumpInsn(Opcodes.GOTO,jump);

    }
}
