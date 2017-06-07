package antlr.astexample;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;

public class ASTOperatorTestCases {
  public static void testExample3(final String testName, final String input) {
    System.out.println("*** ASTOperator (" + testName + ") ***");
    try {
      final ANTLRStringStream ss = new ANTLRStringStream(input);
      final ASTRewriteLexer astopl = new ASTRewriteLexer(ss);
      final CommonTokenStream cts = new CommonTokenStream(astopl);
      final ASTRewriteParser astopp = new ASTRewriteParser(cts);
      astopp.expr();
    } catch (final Exception e) {
      e.printStackTrace();
      Assert.assertTrue(false);
    }
    System.out.println();
    System.out.flush();
  }

  @Test
  public void testExample3_T1() throws Exception {
    ASTOperatorTestCases.testExample3("T1", "4");
  }

  @Test
  public void testExample3_T2() throws Exception {
    ASTOperatorTestCases.testExample3("T2", "4 * 4");
  }

  @Test
  public void testExample3_T3() throws Exception {
    ASTOperatorTestCases.testExample3("T3", "3 + 4");
  }

  @Test
  public void testExample3_T4() throws Exception {
    ASTOperatorTestCases.testExample3("T4", "3 + 4 * 4");
  }
}
