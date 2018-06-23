/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import io.SourceStringReader;
import io.SourceReader;
import error.ErrorHandler;
import scanner.Scanner.Symbol;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author peter
 */
public class ScannerTestNestedComments_urbanides {
    private SourceReader sourceReader;
    private ErrorHandler errorHandler;
    private Scanner scanner;

    public ScannerTestNestedComments_urbanides() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of nextToken method, of class Scanner.
     */
    @Test
    public void testCommentSimple() {
        setupReaderAndErrorHandler("(*This is a comment line*)");
        scanner.nextToken();

        assertTrue("EOFSY expected", scanner.getCurrentToken().getSymbol() == Symbol.EOFSY);
    }
    
    private void setupReaderAndErrorHandler(String srcLine) {
        sourceReader = new SourceStringReader(srcLine);
        errorHandler = new ErrorHandler(sourceReader);
        scanner = new Scanner(sourceReader, errorHandler, new NameManagerForCompiler(sourceReader));
    }
    
    @Test
    public void testCommentSimpleClosed() {
        setupReaderAndErrorHandler("(* This is a comment line *) if");
        scanner.nextToken();

        assertTrue("IFSY expected", scanner.getCurrentToken().getSymbol() == Symbol.IF);
    }
    
    @Test
    public void testCommentNestedOnce() {
        setupReaderAndErrorHandler("(* This is (*a comment line *) if*)");
        scanner.nextToken();

        assertTrue("EOFSY expected", scanner.getCurrentToken().getSymbol() == Symbol.EOFSY);
    }
    
    //Wie implementieren, wenn Comment nicht geschlossen wird
    @Test
    public void testCommentNestedOnceNotClosed() {
        setupReaderAndErrorHandler("(* This (*is a nested*) comment");
        scanner.nextToken();
        errorHandler.throwUnfinishedComment();
        assertTrue("Comment not finished", errorHandler.getLastError().getNumber() == 4);
    }
}
