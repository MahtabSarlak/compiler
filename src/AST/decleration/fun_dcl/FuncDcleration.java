package AST.decleration.fun_dcl;

import AST.block.Block;
import AST.decleration.ArrVar_dcl;
import AST.decleration.SimpleVar_dcl;
import AST.decleration.Var_dcl;
import AST.exp.Return;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class FuncDcleration extends Func_extern {

    ArrayList <Argument > arguments;
    public ArrayList <Return> returns = new ArrayList<Return>();
    Block block;
    public FuncDcleration(String type1, String name, ArrayList<Argument> arguments) {
        super(type1, name, arguments);
    }
    public FuncDcleration(ArrayList <Argument> arguments, String type, String name, Block block){
        super(type, name, arguments);
        this.arguments = arguments;
        this.block = block;
    }
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
    mv = cv.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC,name,this.signature,null,null);
        mv.visitCode();
        SymbolTable.getInstance().addScope(SymbolTable.FUNCTION);
        SymbolTable.setLastFunction(this);

        for (Argument f : arguments){
            if(f.getDimensions()==0){
                Var_dcl v = new SimpleVar_dcl(f.getName(),f.getType().getClassName(),false,false);
                v.compile(mv,cv);
            }else{
                Var_dcl v = new ArrVar_dcl(f.getName(),f.getType().getClassName(),f.getDimensions(),false,false);
                v.compile(mv,cv);
            }
        }

        if(block != null)
        block.compile(mv,cv);

        if(returns.size()==0){
            if (type.toString().equals(Type.VOID_TYPE.toString())){
                mv.visitInsn(Opcodes.RETURN);
            }else{
                throw new RuntimeException("no return type seen , but should have seen one");
            }
        }
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        SymbolTable.getInstance().popScope();
    }
}
