package br.ufsm.ddetector;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Ikarus
 */
public class Parser {
    //CONSTANTS
    static final byte READ = 1;
    static final byte WRITE = 2;
    static final byte BLOCK = 3;
    static final byte UNBLOCK = 4;
    //PRIVATE
    private final File fFile;
    private java.util.ArrayList<Operation>  opList = new java.util.ArrayList<Operation>();

    /*
    public static void main(String... aArgs) throws FileNotFoundException {
        Parser parser = new Parser("C:\\Temp\\test.txt");
        parser.processLineByLine();
    }
    */

    /**
    Constructor.
    @param aFileName full name of an existing, readable file.
     */
    public Parser(String aFileName) {
        fFile = new File(aFileName);
    }

    /** Template method that calls {@link #processLine(String)}.  */
    public final void processLineByLine() throws FileNotFoundException {
        //Note that FileReader is used, not File, since File is not Closeable
        Scanner scanner = new Scanner(new FileReader(fFile));
        try {
            //first use a Scanner to get each line
            while (scanner.hasNextLine()) {
                Operation oper = processLine(scanner.nextLine());
                opList.add(oper);
            }
        } finally {
            //ensure the underlying stream is always closed
            //this only has any effect if the item passed to the Scanner
            //constructor implements Closeable (which it does in this case).
            scanner.close();
        }
    }

    /**
    Overridable method for processing lines in different ways.

    <P>This simple default implementation expects operation-transaction-variable format,
    separated by an ' ' (white space) sign. Examples of valid input :
    <tt>r 1 x</tt>
    <tt>w 1 x</tt>
    <tt>b 2 y</tt>
    <tt>u 2 y</tt>
     */
    protected Operation processLine(String aLine) {
        //use a second Scanner to parse the content of each line
        Scanner scanner = new Scanner(aLine);
        scanner.useDelimiter(" ");
        if (scanner.hasNext()) {
            byte op = 0;
            int trans = 0;
            char var;
            String str = scanner.next();

            if(str.equals("r")) op = READ;
            else if(str.equals("w")) op = WRITE;
            else if(str.equals("b")) op = BLOCK;
            else if(str.equals("u")) op = UNBLOCK;
            else {
                //exception not a valid value
                System.exit(1);
            }
            str = scanner.next();
            if (Character.isDigit(str.charAt(0))) trans = Integer.parseInt(str);
            else {
                //exception not a valid value (needs to be a digit)
                System.exit(1);
            }
            var = scanner.next().charAt(0);

            Operation oper = new Operation(op,trans,var);
            return oper;
        } else {
            System.out.println("Empty or invalid line. Unable to process.");
            System.exit(1);
        }
        //no need to call scanner.close(), since the source is a String
        return new Operation((byte)0,0,'x');
    }

    public java.util.ArrayList<Operation> getList() {
        return opList;
    }
}
