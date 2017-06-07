package sjc.test.extended;

import java.io.File;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;
import org.stringtemplate.v4.ST;

import junit.framework.TestFailure;
import sjc.ast.extended.ExtendedASTUtil;
import sjc.codegen.extended.CodeTranslator;
import sjc.codegen.extended.ExtendedClassByteCodes;
import sjc.symboltable.extended.ExtendedSymbolTable;
import sjc.symboltable.extended.ExtendedSymbolTableBuilder;
import sjc.test.Util;
import sjc.type.TypeFactory;
import sjc.type.checker.extended.ExtendedTypeChecker;
import sjc.type.checker.extended.ExtendedTypeTable;

public class CodeTranslatorTest{

  static class CustomClassLoader extends ClassLoader {
    @SuppressWarnings("rawtypes")
    public Class loadClass(final String name, final byte[] bytecodes) {
      return defineClass(name, bytecodes, 0, bytecodes.length);
    }
  }

  static void outputBytecodes(final PrintWriter pw, final byte[] b) {
    final ClassReader cr = new ClassReader(b);
    final TraceClassVisitor tcv = new TraceClassVisitor(pw);
    cr.accept(tcv, ClassReader.SKIP_FRAMES);
    pw.flush();
  }

  @SuppressWarnings("unchecked")
  public static void testPass(final String filename) {
    final Object[] args = new Object[] { new String[] {} };
    try {
      final String filePath = Util.getResource(
    		  CodeTranslatorTest.class,
          "../../..",
          filename);
      final CompilationUnit cu = ExtendedASTUtil.ast(filePath);
      final ExtendedSymbolTable est = ExtendedSymbolTableBuilder.build(cu);
      final ExtendedTypeTable ett = ExtendedTypeChecker.check(
          new TypeFactory(),
          cu,
          est);
      final ST st = CodeTranslator.generate(
          cu,
          est,
          ett);
      String realFilename = filename.substring(0, filename.indexOf("."));
      System.out.println("Testing File: " + realFilename);
      final String CfilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\c\\" + realFilename;
      File file = new File("src/test/resources/c/" + realFilename + ".c");
      file.createNewFile();
      final PrintWriter pw = new PrintWriter(file);
      pw.write(st.render());
      pw.close();
      ProcessBuilder pb = new ProcessBuilder("gcc", "\"" + CfilePath + "\".c -w -o " + "\"" + CfilePath + ".exe\"");
		//pb.directory(new File("c:\\workspace"));
		//File log = new File("log.txt");
      pb.redirectOutput(Redirect.INHERIT);
      pb.redirectError(Redirect.INHERIT);
      Process p = pb.start();
      p.waitFor();
		//p.sta
      File f = new File(CfilePath + ".exe");
      if(!f.exists()) { 
         System.out.println("Failed to Compile");
      }
      
      //ProcessBuilder pb1 = new ProcessBuilder(CfilePath + ".exe");				//Windows version
      ProcessBuilder pb1 = new ProcessBuilder("./" + CfilePath + ".exe");		//Linux version
      pb1.redirectOutput(Redirect.INHERIT);
      pb1.redirectError(Redirect.INHERIT);
      Process p1 = pb1.start();
      p1.waitFor();
      if(p1.exitValue() !=0){
     	 throw new RuntimeException();
       }
    } catch (final Exception e) {
      e.printStackTrace();
      Assert.assertTrue(e.getMessage(), false);
      throw new RuntimeException();
    }
  }
  
  @SuppressWarnings("unchecked")
  public static void testFail(final String filename) {
    final Object[] args = new Object[] { new String[] {} };
    try {
      final String filePath = Util.getResource(
    		  CodeTranslatorTest.class,
          "../../..",
          filename);
      final CompilationUnit cu = ExtendedASTUtil.ast(filePath);
      final ExtendedSymbolTable est = ExtendedSymbolTableBuilder.build(cu);
      final ExtendedTypeTable ett = ExtendedTypeChecker.check(
          new TypeFactory(),
          cu,
          est);
      final ST st = CodeTranslator.generate(
          cu,
          est,
          ett);

      String realFilename = filename.substring(0, filename.indexOf("."));
      System.out.println("Testing File: " + realFilename);
      final String CfilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\c\\" + realFilename;
      File file = new File("src/test/resources/c/" + realFilename + ".c");
      file.createNewFile();
      final PrintWriter pw = new PrintWriter(file);
      pw.write(st.render());
      pw.close();
      ProcessBuilder pb = new ProcessBuilder("gcc", "\"" + CfilePath + "\".c -w -o " + "\"" + CfilePath + ".exe\"");
		//pb.directory(new File("c:\\workspace"));
		//File log = new File("log.txt");
      pb.redirectOutput(Redirect.INHERIT);
      pb.redirectError(Redirect.INHERIT);
      Process p = pb.start();
      p.waitFor();
		//p.sta
      File f = new File(CfilePath + ".exe");
      if(!f.exists()) { 
         System.out.println("Failed to Compile");
      }
      //ProcessBuilder pb1 = new ProcessBuilder(CfilePath + ".exe");				//Windows version
      ProcessBuilder pb1 = new ProcessBuilder("./" + CfilePath + ".exe");		//Linux version
      pb1.redirectOutput(Redirect.INHERIT);
      pb1.redirectError(Redirect.INHERIT);
      Process p1 = pb1.start();
      p1.waitFor();
      if(p1.exitValue() !=0){
    	 throw new RuntimeException();
      }
    } catch (final Exception e) {
      e.printStackTrace();
      Assert.assertTrue(e.getMessage(), true);
      throw new RuntimeException();
    }
  }

  @Test
  public void testAEEmptyTest() {
    testPass("AEEmptyTest.java");
  }
  

  @Test
  public void testArrayAccessVariable() {
    testPass("ArrayAccessVariable.java");
  }

  @Test
  public void testArrayCreation() {
    testPass("ArrayCreation.java");
  }
 
  
  @Test
  public void testArrayCreation2() {
    testPass("ArrayCreation2.java");
  }
   
 
   @Test
  public void testArrayIndex() {
    testPass("ArrayIndex.java");
  }
  
  @Test
  public void testArrayIndexConstant() {
    testPass("ArrayIndexConstant.java");
  }

 	@Test
  public void testAssignNullToObject() {
    testPass("AssignNullToObject.java");
  }

 @Test
  public void testBasicTypes() {
    testPass("BasicTypes.java");
  }
 @Test
  public void testBinaryOps() {
    testPass("BinaryOps.java");
  }

 @Test
  public void testBooleanAnd() {
    testPass("BooleanAnd.java");
  }
 
 @Test
  public void testBooleanLiteral() {
    testPass("BooleanLiteral.java");
  }

  @Test
  public void testBooleanNot() {
    testPass("BooleanNot.java");
  }

  @Test
  public void testBooleanOr() {
    testPass("BooleanOr.java");
  }

  @Test
  public void testConditionalFalse() {
    testPass("ConditionalFalse.java");
  }

  @Test
  public void testConditionalTrue() {
    testPass("ConditionalTrue.java");
  }

  @Test
  public void testConditionalWithNull() {
    testPass("ConditionalWithNull.java");
  }

  @Test
  public void testCreateIntArrayWithIntAndIntLiteralInitializers() {
    testPass("CreateIntArrayWithIntAndIntLiteralInitializers.java");
  }

  @Test
  public void testDoWhile() {
    testPass("DoWhile.java");
  }

  @Test
  public void testDoWhileWithBooleanArrayAccessCondition() {
    testPass("DoWhileWithBooleanArrayAccessCondition.java");
  }

  @Test
  public void testFactorial() {
    testPass("Factorial.java");
  }

  @Test
  public void testFieldAccess() {
    testPass("FieldAccess.java");
  }

  @Test
  public void testFor() {
    testPass("For.java");
  }
 
  @Test
  public void testForBooleanArrayAccessConditional() {
    testPass("ForBooleanArrayAccessConditional.java");
  }

  @Test
  public void testForBooleanMemberAccessConditional() {
    testPass("ForBooleanMemberAccessConditional.java");
  }

  @Test
  public void testForCondOnly() {
    testPass("ForCondOnly.java");
  }

  @Test
  public void testForEmpty() {
    testPass("ForEmpty.java");
  }

  @Test
  public void testForFull() {
    testPass("ForFull.java");
  }

  @Test
  public void testForIncOrDecOnly() {
    testPass("ForIncOrDecOnly.java");
  }

  @Test
  public void testForInitOnly() {
    testPass("ForInitOnly.java");
  }

  @Test
  public void testForLoop() {
    testPass("ForLoop.java");
  }

  @Test
  public void testForMissingCond() {
    testPass("ForMissingCond.java");
  }

  @Test
  public void testForMissingIncOrDec() {
    testPass("ForMissingIncOrDec.java");
  }

  @Test
  public void testForMissingInit() {
    testPass("ForMissingInit.java");
  }

  @Test
  public void testForMultipleIncOrDecOnly() {
    testPass("ForMultipleIncOrDecOnly.java");
  }

  @Test
  public void testForMultipleInitAndIncOrDec() {
    testPass("ForMultipleInitAndIncOrDec.java");
  }

  @Test
  public void testForMultipleInitOnly() {
    testPass("ForMultipleInitOnly.java");
  }

  @Test
  public void testForwardClassTest() {
    testPass("ForwardClassTest.java");
  }

  @Test
  public void testIf() {
    testPass("If.java");
  }

  @Test
  public void testIfFalseEmpty() {
    testPass("IfFalseEmpty.java");
  }

  @Test
  public void testIfFalseSingle() {
    testPass("IfFalseSingle.java");
  }

  @Test
  public void testIfFalseSingleElseEmpty() {
    testPass("IfFalseSingleElseEmpty.java");
  }

  @Test
  public void testIfFalseSingleElseMultiple() {
    testPass("IfFalseSingleElseMultiple.java");
  }

  @Test
  public void testIfFalseSingleElseSingle() {
    testPass("IfFalseSingleElseSingle.java");
  }

  @Test
  public void testIfTrueEmpty() {
    testPass("IfTrueEmpty.java");
  }

  @Test
  public void testIfTrueEmptyElseEmpty() {
    testPass("IfTrueEmptyElseEmpty.java");
  }

  @Test
  public void testIfTrueMultiple() {
    testPass("IfTrueMultiple.java");
  }

  @Test
  public void testIfTrueSingle() {
    testPass("IfTrueSingle.java");
  }

  @Test
  public void testIfTrueSingleElseEmpty() {
    testPass("IfTrueSingleElseEmpty.java");
  }

  @Test
  public void testIfTrueSingleElseSingle() {
    testPass("IfTrueSingleElseSingle.java");
  }

  @Test
  public void testIncIntArrayAccess() {
    testPass("IncIntArrayAccess.java");
  }

  @Test
  public void testIntAdd() {
    testPass("IntAdd.java");
  }

  @Test
  public void testIntComplement() {
    testPass("IntComplement.java");
  }

  @Test
  public void testIntDivide() {
    testPass("IntDivide.java");
  }

  @Test
  public void testIntEqual() {
    testPass("IntEqual.java");
  }

  @Test
  public void testIntGreaterThan() {
    testPass("IntGreaterThan.java");
  }

  @Test
  public void testIntGreaterThanOrEqual() {
    testPass("IntGreaterThanOrEqual.java");
  }

  @Test
  public void testIntLessThan() {
    testPass("IntLessThan.java");
  }

  @Test
  public void testIntLessThanOrEqual() {
    testPass("IntLessThanOrEqual.java");
  }

  @Test
  public void testIntMultiply() {
    testPass("IntMultiply.java");
  }

  @Test
  public void testIntNegate() {
    testPass("IntNegate.java");
  }

  @Test
  public void testIntNotEqual() {
    testPass("IntNotEqual.java");
  }

  @Test
  public void testIntPlus() {
    testPass("IntPlus.java");
  }

  @Test
  public void testIntPostDecrement() {
    testPass("IntPostDecrement.java");
  }

  @Test
  public void testIntPostIncrement() {
    testPass("IntPostIncrement.java");
  }

  @Test
  public void testIntRemainder() {
    testPass("IntRemainder.java");
  }

  @Test
  public void testIntShiftLeft() {
    testPass("IntShiftLeft.java");
  }

  @Test
  public void testIntShiftRight() {
    testPass("IntShiftRight.java");
  }

  @Test
  public void testIntSubtract() {
    testPass("IntSubtract.java");
  }

  @Test
  public void testIntUnsignedShiftRight() {
    testPass("IntUnsignedShiftRight.java");
  }

  @Test
  public void testNewBasic() {
    testPass("NewBasic.java");
  }

  @Test
  public void testNewID() {
    testPass("NewID.java");
  }

  @Test
  public void testNullArgumentForIntArrayParameter() {
    testPass("NullArgumentForIntArrayParameter.java");
  }

  @Test
  public void testNullEqualsNull() {
    testPass("NullEqualsNull.java");
  }

  @Test
  public void testObjectAEqualsReturnedObjectA() {
    testPass("ObjectAEqualsReturnedObjectA.java");
  }

  @Test
  public void testParens() {
    testPass("Parens.java");
  }

  @Test
  public void testPower() {
    testPass("Power.java");
  }

  @Test
  public void testQueue() {
    testPass("Queue.java");
  }

  @Test
  public void testReturnNullFromIntArrayMethod() {
    testPass("ReturnNullFromIntArrayMethod.java");
  }

  @Test
  public void testSAExample() {
    testPass("SAExample.java");
  }

  @Test
  public void testSymbolTableTest() {
    testPass("SymbolTableTest.java");
  }

  @Test
  public void testSyntaxTorture() {
    testPass("SyntaxTorture.java");
  }

  @Test
  public void testTypesArray() {
    testPass("TypesArray.java");
  }

  @Test
  public void testTypesBasic() {
    testPass("TypesBasic.java");
  }

  @Test
  public void testTypesID() {
    testPass("TypesID.java");
  }

  @Test
  public void testUnaryOps() {
    testPass("UnaryOps.java");
  }

  @Test
  public void testWhile() {
    testPass("While.java");
  }

  
  @Test
  public void testdivideByZero() {
	  testFail("divideByZero.java");
  }

  @Test
  public void testindexOutOfBounds() {
	  testFail("indexOutOfBounds.java");
  }

  
 
  @Test
  public void testarrayReference() {
    testPass("arrayReference.java");
  }
  
  @Test
  public void testassignObjToRef() {
    testPass("assignObjToRef.java");
  }

  @Test
  public void testassignNullToObj() {
    testPass("assignNullToObj.java");
  }
    
    
   @Test
  public void testmilArrayCreation() {
    testPass("milArrayCreation.java");
  }

  @Test
  public void testmilArrayAssignment() {
    testPass("milArrayAssignment.java");
  }

  @Test
  public void testmilArrayFold() {
    testPass("milArrayFold.java");
  }

  @Test
  public void testmilArraySwap() {
    testPass("milArraySwap.java");
  }

  @Test
  public void testobjectArrayField() {
    testPass("objectArrayField.java");
  }

  
  @Test
  public void testarrayInObjectAssign() {
    testPass("arrayInObjectAssign.java");
  }

  @Test
  public void testobjObjAssignment() {
    testPass("objObjAssignment.java");
  }

  @Test
  public void testarrayInObject() {
    testPass("arrayInObject.java");
  }
 
  @Test
  public void onemillionobjecttest() {
    testPass("onemillionobjecttest.java");
  }
 
  
  @Test
  public void testConditionalNullArraytypeTest() {
    testPass("ConditionalNullArraytypeTest.java");
  }

  @Test
  public void testReturnNullFromclassobjectTest() {
    testPass("ReturnNullFromclassobjectTest.java");
  }

  @Test
  public void testWhileInit() {
    testPass("WhileInit.java");
  }
  @Test
  public void testSearchTest() {
    testPass("SearchTest.java");
  }
  

  @Test
  public void testComplicatedFieldArrayAccessTest(){
	  testPass("ComplicatedFieldArrayAccessTest.java");
  }

  @Test
  public void testAssignIntArrayFromIntArrayMethod(){
	  testPass("AssignIntArrayFromIntArrayMethod.java");
  }

  @Test
  public void testMillionObjectsAndFieldInStaticMethod() {
    testPass("MillionObjectsAndFieldInStaticMethod.java");
  }
  
  
  @Test
  public void testTestMillionObjectsAndFieldAndChangeValue() {
    testPass("TestMillionObjectsAndFieldAndChangeValue.java");
  }
  
  @Test
  public void testMillionObjectsCreation() {
    testPass("MillionObjectsCreation.java");
  }
  
  @Test
  public void testMillionObjectsFieldCreation() {
    testPass("MillionObjectsFieldCreation.java");
  }

  @Test
  public void testReturnArrayFromStaticMethodIntArrayMethod() {
    testPass("ReturnArrayFromStaticMethodIntArrayMethod.java");
  }


  
  
  
  
  
  
}
