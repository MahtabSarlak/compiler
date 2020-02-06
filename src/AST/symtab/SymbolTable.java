package AST.symtab;

import AST.decleration.StructDecleration;
import AST.decleration.fun_dcl.FuncDcleration;
import AST.decleration.fun_dcl.Func_extern;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.Label;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {

    public ArrayList<TempMap <String, SimpleDscp> > symtable = new ArrayList<TempMap<String, SimpleDscp>>();
    public static SymbolTable instance = new SymbolTable();
    private jdk.internal.org.objectweb.asm.Label labelLast;
    private jdk.internal.org.objectweb.asm.Label labelFirst;
    private jdk.internal.org.objectweb.asm.Label elseStart;
    private jdk.internal.org.objectweb.asm.Label elseEnd;
    public static Func_extern LastSeenFunction;
    private HashMap<String, ArrayList<Func_extern>> funcDclers = new HashMap<String, ArrayList<Func_extern>>();
    public static SymbolTable getInstance() {
        return instance;
    }

    public static int FUNCTION = 0;
    public static int LOOP = 2;
    public  static  int SWITCH = 1;
    public static int COND_OTHER_THAN_SWITCH = 3;
    public HashMap<String, StructDecleration> structDcls = new HashMap<>();
    private SymbolTable(){
        TempMap<String, SimpleDscp> mainFrame = new TempMap<>();
        mainFrame.setIndex(1);
        symtable.add(mainFrame);
        System.out.println(symtable.size());

    }

    public static Type getTypeFromName(String varType) {
        Type type;
        switch (varType) {
            case "int":
                type = Type.INT_TYPE;
                break;
            case "long":
                type = Type.LONG_TYPE;
                break;
            case "char":
                type = Type.CHAR_TYPE;
                break;
            case "bool":
                type = Type.BOOLEAN_TYPE;
                break;
            case "double":
                type = Type.DOUBLE_TYPE;
                break;
            case "float":
                type = Type.FLOAT_TYPE;
                break;
            case "string":
                type = Type.getType(String.class);
                break;
            case "void":
                type = Type.VOID_TYPE;
                break;
            case "short":
                type = Type.SHORT_TYPE;
                break;
            default:
                type = Type.getType("L" + varType + ";");
        }
        return type;
    }

    public static Func_extern getLastFunction() {
        return LastSeenFunction;
    }

    public static void setLastFunction(Func_extern lastSeenFunction) {
        LastSeenFunction = lastSeenFunction;
    }


    public void addFunction(Func_extern funcDcl) {
        if (funcDclers.containsKey(funcDcl.getName())) {
            funcDclers.get(funcDcl.getName()).add(funcDcl);
        } else {
            ArrayList<Func_extern> funcDclMapper = new ArrayList<>();
            funcDclMapper.add(funcDcl);
            funcDclers.put(funcDcl.getName(), funcDclMapper);
        }
    }

    public Func_extern getFunction(String name, Type[] inputs) {
        if (funcDclers.containsKey(name)) {
            ArrayList<Func_extern> funcs = funcDclers.get(name);
            for (Func_extern f : funcs) {
                if (f.checkfunc(inputs, name)) {
                    return f;
                }
            }
            throw new RuntimeException("no such function was found");
        } else {
            throw new RuntimeException("no such function was found");
        }
    }



    public void addVariable(SimpleDscp dscp, String name) throws Exception {
        if(symtable.get(symtable.size() - 1).containsKey(name)){
            throw new Exception("var was found");
        }
        symtable.get(symtable.size()-1).put(name, dscp);
        symtable.get(symtable.size()-1).index += dscp.type.getSize();
    }

    //todo ....
    public SimpleDscp getDescriptor(String name) throws Exception {
        int i = symtable.size();
        while (i!=0){

            i--;


            if(symtable.get(i).containsKey(name)){
                return symtable.get(i).get(name);
            }
        }
        throw new Exception("var  not found");
    }

    public void addScope(int scopeType){
        TempMap<String, SimpleDscp> frame = new TempMap<>();
        frame.setStartLebel();
        frame.setEndLebel();
        frame.setScopeType(scopeType);
        if (scopeType != FUNCTION)
            frame.setIndex(getLastFrame().getIndex());
        symtable.add(frame);
    }

    public static void add(TempMap<String, SimpleDscp> frame) {
    }

    public void popScope() throws Exception {
       // throw new Exception("removvv");
  //      System.out.println("removing sth");
        symtable.remove(symtable.size()-1);
    }


    int FirstEmpty(){
        return symtable.get(symtable.size()-1).index;
    }


    public void setLabelStart(Label label) {
        getLastFrame().setStartLebel(label);
    }

    public Label getLabelEnd() {
        return getLastFrame().getEndLebel();
    }

    public void setLabelEnd(Label label) {
        getLastFrame().setEndLebel(label);
    }

    public Label getLabelStart() {
        return getLastFrame().getStartLebel();
    }


    public TempMap<String, SimpleDscp> getLastFrame() {
       // System.out.println(symtable.size());
        if (symtable.size() == 0)
            throw new RuntimeException("error");
        return symtable.get(symtable.size() - 1);
    }

    public void addRecord(StructDecleration struct) throws Exception {
        if (structDcls.containsKey(struct.getName()))
            throw new Exception("struct name was used");

        structDcls.put(struct.getName(), struct);
        System.out.println(structDcls.size());
        System.out.println(struct.getName());
    }
    public StructDecleration getStruct(String name) {
        if (structDcls.containsKey(name))
            throw new AST.symtab.NotFound("Record Not Found");

        return structDcls.get(name);
    }

    public boolean isStructDefined(String name){
        try{
            getStruct(name);
            return true;
        }catch (NotFound e){
            return false;
        }
    }
    public void setLabelLast(jdk.internal.org.objectweb.asm.Label labelLast) {
        this.labelLast = labelLast;
    }

    public void setLabelFirst(jdk.internal.org.objectweb.asm.Label labelFirst) {
        this.labelFirst = labelFirst;
    }

    public int returnNewIndex() {
        return getLastFrame().updateIndex();
    }

}
