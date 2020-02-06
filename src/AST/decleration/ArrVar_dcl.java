package AST.decleration;

import AST.exp.Exp;
import AST.symtab.*;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;
import help.HelperFunctions;

import java.util.List;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static jdk.internal.org.objectweb.asm.Opcodes.ASTORE;

public class ArrVar_dcl extends Var_dcl {
    public List<Exp> arrsizes;
    boolean isstatic;
    public String vartype;

    public ArrVar_dcl(String id, String type, List<Exp> arrsize, boolean isstatic, boolean isconst) {
        this.id = id;
        this.arrsizes = arrsize;
        this.isstatic = isstatic;
        this.isconst = isconst;
        this.vartype = type;
    }
    public ArrVar_dcl(String varName, String type, int dims, boolean Static, boolean Constant) {
        this.id = varName;
        this.isstatic = Static;
        this.isconst = Constant;
        this.vartype = type;
    }

    public void addToSymTable(Type type,boolean isconst , boolean isstatic) throws Exception {

        if (id == null || vartype == null)
            throw new IllegalArgumentException();

        for (Exp e:arrsizes) {
            Type t=e.getType();
            if(t!= Type.INT_TYPE&&t!= Type.CHAR_TYPE&&t!= Type.SHORT_TYPE){
                throw new RuntimeException("Index Type should be Integer");
            }
        }
        Type varType = SymbolTable.getTypeFromName(vartype);
        String repeatedArray = new String(new char[arrsizes.size()]).replace("\0", "[");
        type = Type.getType(repeatedArray + varType.getDescriptor());
        SimpleDscp dscp;
        if (isstatic) {
            dscp = new ArrayStaticDscp(id, type, isconst, arrsizes.size());
        } else {
            dscp = new ArrayNonStaticDscp(id, type, isconst ,SymbolTable.getInstance().returnNewIndex(), arrsizes.size());
        }

        SymbolTable.getInstance().addVariable(dscp, id);
    }

    public void addFieldInByteCode(ClassVisitor cv, boolean isstatic) {
        int temp = ACC_PUBLIC;
        temp += isconst() ? Opcodes.ACC_FINAL : 0;
        temp += isstatic ? Opcodes.ACC_STATIC : 0;

        String repeatedArray = new String(new char[arrsizes.size()]).replace("\0", "[");
        jdk.internal.org.objectweb.asm.Type arrayType = jdk.internal.org.objectweb.asm.Type.getType(repeatedArray + type.getDescriptor());

        cv.visitField(temp, getName(), arrayType.getDescriptor(), null, null).visitEnd();

    }
    public void FindType(MethodVisitor mv, ClassVisitor cv)throws Exception {
        Type varType = SymbolTable.getTypeFromName(vartype);
        String repeatedArray = new String(new char[arrsizes.size()]).replace("\0", "[");
        type = Type.getType(repeatedArray + varType.getDescriptor());
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        Type varType = SymbolTable.getTypeFromName(vartype);
        String repeatedArray = new String(new char[arrsizes.size()]).replace("\0", "[");
        type = Type.getType(repeatedArray + varType.getDescriptor());
        addToSymTable( type,isconst ,isstatic);
        SimpleDscp dscp = getDscptor();
        if (dscp instanceof StaticDscp) {
            addFieldInByteCode(cv, true);
        } else {

            for (Exp e : arrsizes) {
                e.compile(mv, cv);
            }
            if (arrsizes.size() == 1) {
                if (!HelperFunctions.isRecord(getType().getElementType())) {
                    mv.visitIntInsn(NEWARRAY, HelperFunctions.getTType(getType().getElementType()));
                } else {
                    mv.visitTypeInsn(ANEWARRAY, getType().getElementType().getInternalName());
                }
            } else {
                mv.visitMultiANewArrayInsn(getType().getDescriptor(), arrsizes.size());
            }

            mv.visitVarInsn(ASTORE, ((NonStaticDscp) getDscptor()).getIndex());
        }
    }

}
