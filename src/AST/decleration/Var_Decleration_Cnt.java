package AST.decleration;

import AST.exp.Exp;

import java.util.ArrayList;

public class Var_Decleration_Cnt {
    public String name ;
    public Exp value ;
    public ArrayList <Exp> arrsize;// delet this
    public Var_Decleration_Cnt(String name, Exp value ,ArrayList<Exp> arrs){
        this.name = name;
        this.value = value;
        this.arrsize=arrs;
    }
}
