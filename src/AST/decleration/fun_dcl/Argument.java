package AST.decleration.fun_dcl;

import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.Type;

public class Argument {
    public Type type;
    public String name;
    public Integer dim;

    public Argument(String type, String name, Integer dim) {
        this.type = FindType(type);
        this.name = name;
        this.dim = dim;
    }
    Type FindType(String s)
    {
       Type type;
        switch (s) {
            case "int":
                type = Type.INT_TYPE;
                break;
            case "long":
                type = Type.LONG_TYPE;
                break;
            case "double":
                type = Type.DOUBLE_TYPE;
                break;
            case "float":
                type = Type.FLOAT_TYPE;
                break;
            case "bool":
                type = Type.BOOLEAN_TYPE;
                break;
            case "char":
                type = Type.CHAR_TYPE;
                break;
            case "string":
                type = Type.getType(String.class);
                break;
            case "void":
                type =Type.VOID_TYPE;
                break;
            case "short":
                type = Type.SHORT_TYPE;
                break;
            default:
                type = Type.getType("L" + s + ";");

        }
        return  type;
    }
    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getDimensions() {
        return dim;
    }
}
