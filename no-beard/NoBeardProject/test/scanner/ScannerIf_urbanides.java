/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import error.ErrorHandler;
import io.SourceReader;
import io.SourceStringReader;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author konst
 */
public class ScannerIf_urbanides {
    private SourceReader sourceReader;
    private ErrorHandler errorHandler;
    private Scanner scanner;

    public ScannerIf_urbanides() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    private void setupReaderAndErrorHandler(String srcLine) {
        sourceReader = new SourceStringReader(srcLine);
        errorHandler = new ErrorHandler(sourceReader);
        scanner = new Scanner(sourceReader, errorHandler, new NameManagerForCompiler(sourceReader));
    }
    
    @Test
    public void testSimpleIf() {
        System.out.println("testSimpleIf");
        
        setupReaderAndErrorHandler("if 1 < 2 do put(HelloWorld); done");
        scanner.nextToken();
        
        assertTrue("if", scanner.getCurrentToken().getSymbol() == Scanner.Symbol.IF);
    }
}
