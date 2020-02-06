package AST.exp.Loop;

import AST.block.Block;
import AST.exp.Statment;

public abstract class LoopStatment extends Statment {
    Block block;

    public LoopStatment(Block block) {
        this.block = block;
    }
}
