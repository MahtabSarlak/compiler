package AST.decleration.fun_dcl;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;

public class Extern_dcl extends Func_extern{
    public Type type;
    public String name;

    public Extern_dcl(String type, String name) {
        super();
        this.type = FindType(type);
        this.name = name;
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

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

    }
}
