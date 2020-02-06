import AST.Program;

import java.io.FileReader;

import help.DefinedValues;

public class Main {
   public static final boolean DEBUG=false;
    public static void main(String[] args) throws Exception {
        parser p = new parser(new Yylex(new FileReader("test.txt")));
       // p.parse();
        //  p.debug_parse();
        Object result = p.parse().value;
        Program pr = (Program) result;
        pr.compile(DefinedValues.nameClass);

    }
}
