package AST.symtab;

import jdk.internal.org.objectweb.asm.Handle;
import jdk.internal.org.objectweb.asm.Label;

import java.util.HashMap;

public class TempMap<K,V> extends HashMap<K,V> {

    int index;
    TempMap(){
        super();
        index=0;
    }
    Label startLebel;
    Label endLebel;
    int scopeType ;

    public Label getStartLebel() {
        return startLebel;
    }


    public void setStartLebel(Label startLebel) {
        this.startLebel = startLebel;
    }
    public void setStartLebel() {
        this.startLebel = new Label();
    }

    public Label getEndLebel() {
        return endLebel;
    }

    public void setEndLebel(Label endLebel) {
        this.endLebel = endLebel;
    }

    public void setEndLebel() {
        this.endLebel = new Label();
    }

    public int getScopeType() {
        return scopeType;
    }

    public void setScopeType(int scopeType) {
        this.scopeType = scopeType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int updateIndex() {
        return index++;
    }
}
