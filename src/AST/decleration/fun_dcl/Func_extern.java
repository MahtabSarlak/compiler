package AST.decleration.fun_dcl;

import AST.Node;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.Type;

import java.util.ArrayList;

public abstract class Func_extern extends Node {

    protected Type type;
    protected String name;
    protected String signature;
    public Type [] inputs;

    public Func_extern() {

    }

    public String getSignature(){
        return signature;
    }
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Func_extern(String type1, String name, ArrayList<Argument > arguments) {
        this.type = SymbolTable.getTypeFromName(type1);
        this.name = name;
        inputs = new Type[arguments.size()];
        int i = 0;
        for(Argument f : arguments){
            inputs[i++]=f.getType();
        }


        String signature = "";
        signature = signature + "(";
        for(Type typeIn : inputs){
            signature = signature+typeIn.toString();
        }
        signature = signature + ")";
        signature = signature + type.toString();
        this.signature = signature;

        try {
            SymbolTable.getInstance().getFunction(name,inputs);
        }catch (RuntimeException r){
            SymbolTable.getInstance().addFunction(this);
        }

    }

    public boolean checkfunc(Type[] inputs, String name){

        if (!this.name.equals(name)){
            return false;
        }else if (this.inputs.length!=inputs.length){
            return false;
        }else {
            int i = 0;
            for(Type t : inputs){
                if(!this.inputs[i++].equals(t)){
                    return false;
                }
            }
            return true;

        }
    }
}
