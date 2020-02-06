package AST.decleration;

import AST.Node;
import AST.TempNode;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import help.DefinedValues;

import java.util.ArrayList;

public class Var_decleration extends TempNode {
    public String type;
    public  boolean isconst;
    //public int arrsize     some changes down
    public  ArrayList<Var_Decleration_Cnt> var_decl_cnts;
    ArrayList<Var_dcl> myVarDcl = new ArrayList<Var_dcl>();

    public Var_decleration(String type, boolean isconst,ArrayList<Var_Decleration_Cnt>dcl_cnt) {
        this.type = type;
        this.isconst = isconst;
        this.var_decl_cnts =dcl_cnt;

        for (Var_Decleration_Cnt v : this.var_decl_cnts) {
            if(v.arrsize==null) System.out.println("===");
//            if(v.arrsize.size()==0) System.out.println("{{{");
            if (v.arrsize==null||v.arrsize.size() == 0)
            {
                if(v.value ==null)
                {
                    myVarDcl.add(new SimpleVar_dcl(v.name,type,isconst, DefinedValues.getScope()));
                }else{
                    myVarDcl.add(new SimpleVar_dcl(v.name,type,v.value,isconst, DefinedValues.getScope()));
                }
            }else{
                myVarDcl.add(new ArrVar_dcl(v.name,type,v.arrsize, DefinedValues.getScope() ,isconst));
            }
        }


    }
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {
        for (Var_dcl v : myVarDcl) {
            v.compile(mv, cv);
        }
    }
    public ArrayList<Var_dcl> getVariables() {
        return myVarDcl;
    }

}
