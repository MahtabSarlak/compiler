package AST.decleration;

import AST.Node;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import help.DefinedValues;
import help.HelperFunctions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class StructDecleration extends Node {
    private HashMap<String, Var_dcl> variables = new HashMap<>();
    private String name;
    private jdk.internal.org.objectweb.asm.Type type;

    public StructDecleration(ArrayList<Var_decleration> dclses, String name) throws Exception {
        this.name = name;

        for (Var_decleration dcls : dclses) {
            ArrayList<Var_dcl> vars = dcls.getVariables();
            for (Var_dcl var : vars) {
                if (variables.containsKey(var.getName())) throw new Exception("duplicate name in struct");
                variables.put(var.getName(), var);
            }
        }
        type = jdk.internal.org.objectweb.asm.Type.getType("L" + name + ";");
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) throws Exception {

        SymbolTable.getInstance().addRecord(this);
        ClassWriter cw;
        TraceClassVisitor tcv;
        cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        try {
            tcv = new TraceClassVisitor(cw, new PrintWriter(Paths.get(DefinedValues.compilePath, getName() + ".txt").toFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        tcv.visit(V1_8, ACC_PUBLIC + ACC_SUPER, getName(), null, Type.getInternalName(Object.class), null);
        for (Var_dcl var : variables.values()) {
            var.FindType(null, null);
            if (HelperFunctions.isRecord(var.getType()) && !SymbolTable.getInstance().isStructDefined(var.getType().getClassName()))
                throw new Exception("struct Type Is Not Defined");
            var.addFieldInByteCode(cv, false);
        }


        jdk.internal.org.objectweb.asm.MethodVisitor tmv;
        tmv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        tmv.visitCode();
        tmv.visitVarInsn(ALOAD, 0);
        tmv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        tmv.visitInsn(RETURN);
        tmv.visitMaxs(0, 0);
        tmv.visitEnd();

        tcv.visitEnd();
        byte[] bytes = cw.toByteArray();
        try {
            Files.write(Paths.get(DefinedValues.compilePath,  getName() + ".class"), bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
