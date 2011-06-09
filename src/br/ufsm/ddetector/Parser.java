package br.ufsm.ddetector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Ikarus
 */
public class Parser {
    
    static final byte READ = 1;
    static final byte WRITE = 2;
    static final byte BLOCK = 3;
    static final byte UNBLOCK = 4;
    
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
                processLine(scanner.nextLine());
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

    <P>This simple default implementation expects simple name-value pairs, separated by an
    '=' sign. Examples of valid input :
    <tt>height = 167cm</tt>
    <tt>mass =  65kg</tt>
    <tt>disposition =  "grumpy"</tt>
    <tt>this is the name = this is the value</tt>
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
            log("Empty or invalid line. Unable to process.");
            System.exit(1);
        }
        return null;
        //no need to call scanner.close(), since the source is a String
    }
    // PRIVATE
    private final File fFile;

    private static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }

    private String quote(String aText) {
        String QUOTE = "'";
        return QUOTE + aText + QUOTE;
    }
}
