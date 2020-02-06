package AST.exp;

import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class Continue extends Statment {
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

        if(SymbolTable.getInstance().getLastFrame().getScopeType()== SymbolTable.getInstance().LOOP ||SymbolTable.getInstance().getLastFrame().getScopeType()== SymbolTable.getInstance().SWITCH )
        {
            mv.visitJumpInsn(Opcodes.GOTO ,SymbolTable.getInstance().getLabelStart());
        }else
        {
            throw new RuntimeException("continue is just for swich or loop");
        }
    }
}
