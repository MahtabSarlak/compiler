package AST.symtab;

import jdk.internal.org.objectweb.asm.*;

public class Dscptor {

    String name;

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    Type type;

    public Dscptor(String name, Type type ) {
        this.type = type;
        this.name = name;
    }

}
