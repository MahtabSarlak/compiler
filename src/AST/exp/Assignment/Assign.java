package AST.exp.Assignment;

import AST.symtab.Dscptor;
import AST.symtab.Dscptor.*;
import AST.exp.Exp;
import AST.exp.Statment;
import AST.exp.variables.SimpleVariable;
import AST.exp.variables.Variables;
import AST.symtab.Dscptor;
import AST.symtab.SimpleDscp;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.ISTORE;

public abstract class Assign extends Exp {
    Variables var;
    Exp exp1;
    Type type1,type2;

    public Assign(Variables var, Exp exp1) {
        this.var = var;
        this.exp1 = exp1;

    }
}
