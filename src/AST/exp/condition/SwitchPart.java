package AST.exp.condition;

import AST.block.Block;
import AST.exp.Exp;
import AST.exp.Statment;
import AST.exp.condition.CasePart;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.ArrayList;

public class SwitchPart extends Statment {
    Exp exp;
    ArrayList<CasePart> cases;
    Block blockDefault;
    Label labelDefault=new Label();
    Label end =new Label();
    Label lookUpTable = new Label();
    Label start = new Label();
    //TODO keep in mind that the array list can be empty
    public SwitchPart(Exp exp, ArrayList <CasePart> cases, Block blockDefault){
        this.exp = exp ;
        this.cases = cases;
        this.blockDefault = blockDefault;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        SymbolTable.getInstance().addScope(SymbolTable.SWITCH);
        SymbolTable.getInstance().setLabelFirst(start);
        SymbolTable.getInstance().setLabelLast(end);
        Label [] labels = new Label[cases.size()];
        int [] ints = new int[cases.size()];
        int i = 0 ;
        mv.visitLabel(start);
        exp.compile(mv,cv);
        mv.visitJumpInsn(Opcodes.GOTO,lookUpTable);
        for(CasePart c : cases){
            c.jump = end;
            c.start = start;
            c.compile(mv,cv);
            labels[i]=c.labelStartCase;
            ints[i++]=c.int_const.value;
        }
        mv.visitLabel(labelDefault);
        blockDefault.compile(mv,cv);
        mv.visitJumpInsn(Opcodes.GOTO,end);
        mv.visitLabel(lookUpTable);
        mv.visitLookupSwitchInsn(labelDefault,ints,labels);
        mv.visitLabel(end);
        SymbolTable.getInstance().popScope();
    }
}
