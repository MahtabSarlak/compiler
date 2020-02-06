package AST.decleration;

import AST.exp.Exp;
import AST.exp.constant.Constant;
import AST.symtab.*;
import jdk.internal.org.objectweb.asm.*;
import help.tcv;
import help.tmv;

import static jdk.internal.org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static jdk.internal.org.objectweb.asm.Opcodes.ISTORE;

public class SimpleVar_dcl extends Var_dcl{
   public boolean isstatic;
  public   String vartype;

    public SimpleVar_dcl(String id, String vartype, boolean Constant, boolean isstatic) {
        this.id = id;
        this.vartype = vartype;
        this.isstatic = isstatic;
        this.isconst = Constant;

    }

    public SimpleVar_dcl(String id, String vartype, Exp value, boolean isconst, boolean isstatic) {
        this.id = id;
        this.vartype = vartype;
        this.exp = value;
        this.isstatic = isstatic;
        this.isconst = isconst;

        if (value == null && vartype.equals("auto"))
            throw new RuntimeException("no value");
    }
    public void FindType(MethodVisitor mv, ClassVisitor cv) throws Exception {
        tcv dcv = new tcv(0);
        tmv dmv = new tmv(0);

        if (!vartype.equals("auto"))
            type = SymbolTable.getTypeFromName(vartype);
        else {
            getExp().compile(dmv, dcv);
            type = getExp().getType();
        }
    }
    public void addToSymTable(Type type,boolean isconst , boolean isstatic) throws Exception {
        if(id==null) System.out.println("<___");
        if(type==null) System.out.println(":::");
        if (id == null || type == null)
            throw new Exception("not accepted Simplevar decleration");

        SimpleDscp d;

        if (isstatic) {
            d = new SimpleStaticDscp(id, type, isconst);
        } else {
            d = new SimpleNonStaticDscp(id, type,isconst, SymbolTable.getInstance().returnNewIndex());

        }

        SymbolTable.getInstance().addVariable(d, id);
    }

    public void addFieldInByteCode(ClassVisitor cv, boolean isstatic) throws Exception {
        Object value = null;
        if (getExp() instanceof Constant && getExp().getType().equals(getType())) {
            value = ((Constant) getExp()).getValue();
        }
        int temp = ACC_PUBLIC;
        temp += isconst() ? Opcodes.ACC_FINAL : 0;
        temp += isstatic ? Opcodes.ACC_STATIC : 0;

        FieldVisitor fv = cv.visitField(temp, getName(), getType().getDescriptor(), null, value);
        fv.visitEnd();
    }


    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        tcv dcv = new tcv(0);
        tmv dmv = new tmv(0);

        if (!vartype.equals("auto"))
            type = SymbolTable.getTypeFromName(vartype);
        else {
            getExp().compile(dmv, dcv);
            type = getExp().getType();
        }
        addToSymTable(type, isconst , isstatic);
        SimpleDscp dscp = getDscptor();
        if (dscp instanceof StaticDscp) {
            addFieldInByteCode(cv, true);
        } else {
            NonStaticDscp simpleDs = (NonStaticDscp) dscp;
            if (getExp() != null && getExp().getType().equals(getType())) {
                getExp().compile(mv, cv);
                mv.visitVarInsn(getType().getOpcode(ISTORE), simpleDs.getIndex());
            }
        }

    }

    public Exp getExp() {
        return super.getExp();
    }

}
