package AST;


import AST.decleration.Var_decleration;
import AST.decleration.StructDecleration;
import AST.decleration.fun_dcl.FuncDcleration;
import AST.decleration.fun_dcl.Func_extern;
import AST.symtab.SymbolTable;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;
import help.DefinedValues;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class Program
{

    public ArrayList<Node> nodes = new ArrayList<Node>();

   /* public static void main(String[] args) throws Exception {
        ASMifier.main(new String[]{"out/production/compiler_part2/AA.class"});
    }*/
    public Program() {
    }

    public Program add(Node e) {
        nodes.add(e);
        return this;
    }

    public void compile(String savePath) throws Exception {
        ClassWriter cw;
        TraceClassVisitor cv;
        MethodVisitor mv;
        cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cv = new TraceClassVisitor(cw, new PrintWriter(Paths.get(DefinedValues.compilePath, DefinedValues.nameClass + ".txt").toFile()));
        cv.visit(V1_8, ACC_PUBLIC + ACC_SUPER, DefinedValues.nameClass, null, Type.getInternalName(Object.class), null);
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        for(Node n:nodes)
        {
            if(n instanceof Var_decleration)
            {
                n.compile(mv,cv);
            }
        }
        for(Node n:nodes)
        {
            if(n instanceof StructDecleration)
            {
                n.compile(mv,cv);
            }
        }
        for(Node n:nodes)
        {
            if(n instanceof FuncDcleration)
            {
                n.compile(mv,cv);
            }
        }

        mv = cv.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();

        try {
            Func_extern f = SymbolTable.getInstance().getFunction("start", new Type[0]);
            if (!f.getType().equals(Type.VOID_TYPE)) {
                throw new RuntimeException("your start should return void");
            }
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, DefinedValues.nameClass, f.getName(), f.getSignature(), false);
        } catch (RuntimeException r) {
            throw new RuntimeException("there was no start in the program");
        }

        mv.visitInsn(RETURN);

        mv.visitMaxs(0, 0);
        mv.visitEnd();
        cv.visitEnd();
        byte[] bytes = cw.toByteArray();

        try {
            Files.write(Paths.get(DefinedValues.compilePath, DefinedValues.nameClass+".class"), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DynamicClassLoader loader = new DynamicClassLoader();
        Class<?> clazz = loader.defineClass(DefinedValues.nameClass, bytes);
        Method main = clazz.getMethod("main", String[].class);
        String[] arguments = new String[0];
        main.invoke(null, (Object) arguments);
    }
}

class DynamicClassLoader extends ClassLoader {
    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
