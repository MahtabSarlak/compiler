package AST.symtab;

import jdk.internal.org.objectweb.asm.Type;

import java.util.ArrayList;

public class ArrayDscptor extends Dscptor {
    ArrayList<Integer> arrayList=new ArrayList<>();
    public ArrayDscptor(String name, Type type , int ... arraySize) {
        super(name, type);
        for (int d:arraySize) {
            this.arrayList.add(d);
        }
    }

    public ArrayList getArraysize() {
        return arrayList;
    }

}
