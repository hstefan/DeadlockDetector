package br.ufsm.ddetector;

/**
 *
 * @author Ikarus
 */
public class Operation {
    static final byte READ = 1;
    static final byte WRITE = 2;
    static final byte BLOCK = 3;
    static final byte UNBLOCK = 4;

    private byte operation;
    private int transaction;
    private int variable;

    public Operation(byte op, int trans, int var) {
        operation = op;
        transaction = trans;
        variable = var;
    }

    public byte getOp() {
        return operation;
    }

    public int getTrans() {
        return transaction;
    }

    public int getVar() {
        return variable;
    }
}
