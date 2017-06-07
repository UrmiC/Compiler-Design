package sjc.ast.extended;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

import org.antlr.v4.runtime.ParserRuleContext;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;


import sjc.parser.extended.ExtendedStaticJavaBaseVisitor;
import sjc.parser.extended.ExtendedStaticJavaParser.ArgsContext;
import sjc.parser.extended.ExtendedStaticJavaParser.AssignStatementContext;
import sjc.parser.extended.ExtendedStaticJavaParser.AssignContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ArrayAccessContext;
import sjc.parser.extended.ExtendedStaticJavaParser.CreateArrayExpContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ArrayInitContext;
import sjc.parser.extended.ExtendedStaticJavaParser.CreateArrayInitContext;
import sjc.parser.extended.ExtendedStaticJavaParser.BinaryExpContext;
import sjc.parser.extended.ExtendedStaticJavaParser.BooleanTypeContext;
import sjc.parser.extended.ExtendedStaticJavaParser.BasicTypeArrayContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ClassTypeArrayContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ClassTypeIdContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ClassDefinitionContext;
import sjc.parser.extended.ExtendedStaticJavaParser.CondExpContext;
import sjc.parser.extended.ExtendedStaticJavaParser.CreateClassContext;
import sjc.parser.extended.ExtendedStaticJavaParser.DoWhileStatementContext;
import sjc.parser.extended.ExtendedStaticJavaParser.SimpleClassDeclarationContext;
import sjc.parser.extended.ExtendedStaticJavaParser.CompilationUnitContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ExpContext;
import sjc.parser.extended.ExtendedStaticJavaParser.FalseLiteralContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ForInitContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ForUpdateContext;
import sjc.parser.extended.ExtendedStaticJavaParser.PublicFieldDeclarationContext;
import sjc.parser.extended.ExtendedStaticJavaParser.FieldDeclarationContext;
import sjc.parser.extended.ExtendedStaticJavaParser.FieldOrMethodDeclarationContext;
import sjc.parser.extended.ExtendedStaticJavaParser.FieldAccessContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ForStatementContext;
import sjc.parser.extended.ExtendedStaticJavaParser.IdExpContext;
import sjc.parser.extended.ExtendedStaticJavaParser.IfStatementContext;
import sjc.parser.extended.ExtendedStaticJavaParser.IntLiteralContext;
import sjc.parser.extended.ExtendedStaticJavaParser.IntTypeContext;
import sjc.parser.extended.ExtendedStaticJavaParser.InvokeContext;
import sjc.parser.extended.ExtendedStaticJavaParser.InvokeExpContext;
import sjc.parser.extended.ExtendedStaticJavaParser.InvokeExpStatementContext;
import sjc.parser.extended.ExtendedStaticJavaParser.IncdecStatementContext;
import sjc.parser.extended.ExtendedStaticJavaParser.IncdecContext;
import sjc.parser.extended.ExtendedStaticJavaParser.LocalDeclarationContext;
import sjc.parser.extended.ExtendedStaticJavaParser.MainMethodDeclarationContext;
import sjc.parser.extended.ExtendedStaticJavaParser.MethodBodyContext;
import sjc.parser.extended.ExtendedStaticJavaParser.MethodDeclarationContext;
import sjc.parser.extended.ExtendedStaticJavaParser.NonVoidReturnTypeContext;
import sjc.parser.extended.ExtendedStaticJavaParser.NullLiteralContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ParamContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ParamsContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ParenExpContext;
import sjc.parser.extended.ExtendedStaticJavaParser.ReturnStatementContext;
import sjc.parser.extended.ExtendedStaticJavaParser.StatementContext;
import sjc.parser.extended.ExtendedStaticJavaParser.TrueLiteralContext;
import sjc.parser.extended.ExtendedStaticJavaParser.UnaryExpContext;
import sjc.parser.extended.ExtendedStaticJavaParser.VoidTypeContext;
import sjc.parser.extended.ExtendedStaticJavaParser.WhileStatementContext;
import sjc.parser.extended.ExtendedStaticJavaParser.AccessArrayContext;
import sjc.parser.extended.ExtendedStaticJavaParser.AccessFieldContext;
import sjc.parser.extended.ExtendedStaticJavaParser.AccessVarContext;

/**
 * This class builds JDT AST from ANTLR Parse Tree produced by ExtendedStaticJava
 * parser.
 *
 * @author <a href="mailto:robby@cis.ksu.edu">Robby</a>
 */
public class ExtendedStaticJavaASTBuilder extends ExtendedStaticJavaBaseVisitor<ASTNode> {

  static CompilationUnit ast(final CompilationUnitContext ctx) {
    final ExtendedStaticJavaASTBuilder builder = new ExtendedStaticJavaASTBuilder();
    return builder.<CompilationUnit> build(ctx);
  }

  final static HashMap<String, InfixExpression.Operator> binopMap;

  final static HashMap<String, PrefixExpression.Operator> unopMap;
  
  final static HashMap<String, PostfixExpression.Operator> incdecopMap;

  static {
	    binopMap = new HashMap<String, InfixExpression.Operator>(16);
	    ExtendedStaticJavaASTBuilder.binopMap.put("+", InfixExpression.Operator.PLUS);
	    ExtendedStaticJavaASTBuilder.binopMap.put("-", InfixExpression.Operator.MINUS);
	    ExtendedStaticJavaASTBuilder.binopMap.put("*", InfixExpression.Operator.TIMES);
	    ExtendedStaticJavaASTBuilder.binopMap.put("/", InfixExpression.Operator.DIVIDE);
	    ExtendedStaticJavaASTBuilder.binopMap.put("%", InfixExpression.Operator.REMAINDER);
	    ExtendedStaticJavaASTBuilder.binopMap.put(">", InfixExpression.Operator.GREATER);
	    ExtendedStaticJavaASTBuilder.binopMap.put(">=",InfixExpression.Operator.GREATER_EQUALS);
	    ExtendedStaticJavaASTBuilder.binopMap.put("==",InfixExpression.Operator.EQUALS);
	    ExtendedStaticJavaASTBuilder.binopMap.put("!=",InfixExpression.Operator.NOT_EQUALS);
	    ExtendedStaticJavaASTBuilder.binopMap.put("==", InfixExpression.Operator.EQUALS);
	    ExtendedStaticJavaASTBuilder.binopMap.put("<", InfixExpression.Operator.LESS);
	    ExtendedStaticJavaASTBuilder.binopMap.put("<=",InfixExpression.Operator.LESS_EQUALS);
	    ExtendedStaticJavaASTBuilder.binopMap.put("&&",InfixExpression.Operator.CONDITIONAL_AND);
	    ExtendedStaticJavaASTBuilder.binopMap.put("||",InfixExpression.Operator.CONDITIONAL_OR);
	    ExtendedStaticJavaASTBuilder.binopMap.put("<<",InfixExpression.Operator.LEFT_SHIFT);
	    ExtendedStaticJavaASTBuilder.binopMap.put(">>",InfixExpression.Operator.RIGHT_SHIFT_SIGNED);
	    ExtendedStaticJavaASTBuilder.binopMap.put(">>>",InfixExpression.Operator.RIGHT_SHIFT_UNSIGNED);
	    
	    
	    unopMap = new HashMap<String, PrefixExpression.Operator>(4);
	    ExtendedStaticJavaASTBuilder.unopMap.put("+", PrefixExpression.Operator.PLUS);
	    ExtendedStaticJavaASTBuilder.unopMap.put("-", PrefixExpression.Operator.MINUS);
	    ExtendedStaticJavaASTBuilder.unopMap.put("!", PrefixExpression.Operator.NOT);
	    ExtendedStaticJavaASTBuilder.unopMap.put("~", PrefixExpression.Operator.COMPLEMENT);
	    
	    incdecopMap =new HashMap<String, PostfixExpression.Operator>(2);
	    ExtendedStaticJavaASTBuilder.incdecopMap.put("++", PostfixExpression.Operator.INCREMENT);
	    ExtendedStaticJavaASTBuilder.incdecopMap.put("--", PostfixExpression.Operator.DECREMENT);
	  
	  }

  protected AST ast = AST.newAST(AST.JLS8);

  private ExtendedStaticJavaASTBuilder() {
  }

  @SuppressWarnings("unchecked")
  private void add(@SuppressWarnings("rawtypes") final List l, final Object o) {
    l.add(o);
  }

  @SuppressWarnings("unchecked")
  private <T extends ASTNode> T build(final ParserRuleContext tree) {
    return (T) visit(tree);
  }

  private <E extends ParserRuleContext> void builds(
      @SuppressWarnings("rawtypes") final List l, final List<E> trees) {
    if (trees != null) {
      for (final E e : trees) {
        add(l, build(e));
      }
    }
  }

  
  @Override
  public CompilationUnit visitCompilationUnit(final CompilationUnitContext ctx) {
    final CompilationUnit result = this.ast.newCompilationUnit();
    
	  final List<SimpleClassDeclarationContext> simpleClassDefinition = ctx.sc1;
	  if(simpleClassDefinition.size()!=0){
		  builds(result.types(),simpleClassDefinition);
	  }
	  
    add(result.types(), this.<TypeDeclaration> build(ctx.cd));
    
	  final List<SimpleClassDeclarationContext> simpleClassDefinition2 = ctx.sc2;
	  if(simpleClassDefinition2!=null){
		  builds(result.types(),simpleClassDefinition2);
	  }

    return result;
  }
  
  @Override
  public TypeDeclaration visitSimpleClassDeclaration(final SimpleClassDeclarationContext ctx) {
	  final TypeDeclaration result = this.ast.newTypeDeclaration();
	  result.setName(this.ast.newSimpleName(ctx.ID().getText()));
	  final List<PublicFieldDeclarationContext> publicFieldDeclarations = ctx.publicFieldDeclaration();
	    if (publicFieldDeclarations != null) {
	      builds(result.bodyDeclarations(), ctx.publicFieldDeclaration());
	    }
	    return result;
  }
  
   @Override
  public TypeDeclaration visitClassDefinition(final ClassDefinitionContext ctx) {
    final TypeDeclaration result = this.ast.newTypeDeclaration();
    add(result.modifiers(),this.ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
     result.setName(this.ast.newSimpleName(ctx.ID().getText()));
     add(
        result.bodyDeclarations(),
        this.<MethodDeclaration> build(ctx.mainMethodDeclaration()));

   /*  final List<FieldDeclarationContext> fieldDeclarations = ctx.fieldDeclaration();
     if (fieldDeclarations != null) {
       builds(result.bodyDeclarations(), fieldDeclarations);
     }

     final List<MethodDeclarationContext> methodDeclarations = ctx.methodDeclaration();
     if (methodDeclarations != null) {
       builds(result.bodyDeclarations(), methodDeclarations);
     }*/
     final List<FieldOrMethodDeclarationContext> fieldDeclarations = ctx.fieldOrMethodDeclaration();
     if (fieldDeclarations != null) {
       builds(result.bodyDeclarations(), fieldDeclarations);
     }
     
     
     return result;
   }
   @Override
  public MethodDeclaration visitMainMethodDeclaration(final MainMethodDeclarationContext ctx) {
     final MethodDeclaration result = this.ast.newMethodDeclaration();
     add(
         result.modifiers(),
         this.ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
     add(
         result.modifiers(),
         this.ast.newModifier(Modifier.ModifierKeyword.STATIC_KEYWORD));
     result.setReturnType2(this.ast.newPrimitiveType(PrimitiveType.VOID));
     result.setName(this.ast.newSimpleName("main"));

     final SingleVariableDeclaration svd = this.ast
         .newSingleVariableDeclaration();
     svd.setType(this.ast.newArrayType(this.ast.newSimpleType(this.ast
         .newSimpleName("String"))));
     svd.setName(this.ast.newSimpleName(ctx.id3.getText()));
     add(result.parameters(), svd);

     result.setBody(this.<Block> build(ctx.methodBody()));

     return result;
   }

   
 @Override
  public FieldDeclaration visitFieldDeclaration(
      final FieldDeclarationContext ctx) {
    final VariableDeclarationFragment vdf = this.ast
        .newVariableDeclarationFragment();
    final FieldDeclaration result = this.ast.newFieldDeclaration(vdf);
    add(
        result.modifiers(),
        this.ast.newModifier(Modifier.ModifierKeyword.STATIC_KEYWORD));

    result.setType(this.<Type> build(ctx.type()));

    vdf.setName(this.ast.newSimpleName(ctx.ID().getText()));
    

    return result;
  }
 
 
 
 @Override
  public FieldDeclaration visitPublicFieldDeclaration(
     final PublicFieldDeclarationContext ctx) {
   final VariableDeclarationFragment vdf = this.ast
       .newVariableDeclarationFragment();
   final FieldDeclaration result = this.ast.newFieldDeclaration(vdf);
   add(
       result.modifiers(),
       this.ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));

   result.setType(this.<Type> build(ctx.type()));

   vdf.setName(this.ast.newSimpleName(ctx.ID().getText()));
   

   return result;
 }
 
 
 @Override
  public MethodDeclaration visitMethodDeclaration(
     final MethodDeclarationContext ctx) {
   final MethodDeclaration result = this.ast.newMethodDeclaration();
   add(
       result.modifiers(),
       this.ast.newModifier(Modifier.ModifierKeyword.STATIC_KEYWORD));

   result.setReturnType2(this.<Type> build(ctx.returnType()));

   result.setName(this.ast.newSimpleName(ctx.ID().getText()));

   final ParamsContext params = ctx.params();
   if (params != null) {
     builds(result.parameters(), params.param());
   }

   result.setBody(this.<Block> build(ctx.methodBody()));

   return result;
 }




 @Override
  public SingleVariableDeclaration visitParam(final ParamContext ctx) {
   final SingleVariableDeclaration result = this.ast
       .newSingleVariableDeclaration();

   result.setType(this.<Type> build(ctx.type()));

   result.setName(this.ast.newSimpleName(ctx.ID().getText()));

   return result;
 }
 
 @Override
  public Block visitMethodBody(final MethodBodyContext ctx) {
   final Block result = this.ast.newBlock();

   final List<LocalDeclarationContext> localDeclarations = ctx
       .localDeclaration();
   if (localDeclarations != null) {
     builds(result.statements(), localDeclarations);
   }

   final List<StatementContext> statements = ctx.statement();
   if (statements != null) {
     builds(result.statements(), statements);
   }

   return result;
 }
  
 
 @Override
  public VariableDeclarationStatement visitLocalDeclaration(
     final LocalDeclarationContext ctx) {
   final VariableDeclarationFragment vdf = this.ast
       .newVariableDeclarationFragment();
   vdf.setName(this.ast.newSimpleName(ctx.ID().getText()));
   final VariableDeclarationStatement result = this.ast
       .newVariableDeclarationStatement(vdf);

   result.setType(this.<Type> build(ctx.type()));

   

   return result;
 }
 

 @Override
 public ArrayType visitClassTypeArray(final ClassTypeArrayContext ctx){
	final ArrayType result = this.ast.newArrayType(this.<ArrayType> build(ctx.classType()));
	return result;
	  
 }

 @Override
 public ArrayType visitBasicTypeArray(final BasicTypeArrayContext ctx){
	final ArrayType result = this.ast.newArrayType(this.<ArrayType> build(ctx.basicType()));
	return result;
	  
 }
 
 @Override
 public SimpleType visitClassTypeId(final ClassTypeIdContext ctx) {
   return this.ast.newSimpleType(this.ast.newSimpleName(ctx.ID().getText()));
 }
 
 @Override
 public PrimitiveType visitIntType(final IntTypeContext ctx) {
   return this.ast.newPrimitiveType(PrimitiveType.INT);
 }
 
 @Override
 public PrimitiveType visitBooleanType(final BooleanTypeContext ctx) {
   return this.ast.newPrimitiveType(PrimitiveType.BOOLEAN);
 }
 
 @Override
 public PrimitiveType visitVoidType(final VoidTypeContext ctx) {
   return this.ast.newPrimitiveType(PrimitiveType.VOID);
 }
  
 @Override
 public Type visitNonVoidReturnType(final NonVoidReturnTypeContext ctx) {
   return this.<Type> build(ctx.type());
 } 
 

 @Override
  public ExpressionStatement visitAssignStatement(
      final AssignStatementContext ctx) {
    final Assignment a = this.ast.newAssignment();
    

    a.setLeftHandSide(this.<Expression> build(ctx.lhs()));

    a.setRightHandSide(this.<Expression> build(ctx.exp()));
    
    final ExpressionStatement result = this.ast.newExpressionStatement(a);

    return result;
  }
 
 @Override
 public Assignment visitAssign(final AssignContext ctx) {
   final Assignment result= this.ast.newAssignment();
   

   result.setLeftHandSide(this.<Expression> build(ctx.lhs()));

   result.setRightHandSide(this.<Expression> build(ctx.exp()));

   return result;
 }
 
 @Override
 public IfStatement visitIfStatement(final IfStatementContext ctx) {
   final IfStatement result = this.ast.newIfStatement();
   final Block thenBlock = this.ast.newBlock();
   result.setThenStatement(thenBlock);
   final Block elseBlock = this.ast.newBlock();
   result.setElseStatement(elseBlock);

   result.setExpression(this.<Expression> build(ctx.exp()));

   final List<StatementContext> ts = ctx.ts;
   if (ts != null) {
     builds(thenBlock.statements(), ts);
   }

   final List<StatementContext> fs = ctx.fs;
   if (fs != null) {
     builds(elseBlock.statements(), fs);
   }

   return result;
 }
 
 
 @Override
 public WhileStatement visitWhileStatement(final WhileStatementContext ctx) {
   final WhileStatement result = this.ast.newWhileStatement();
   final Block whileBody = this.ast.newBlock();
   result.setBody(whileBody);

   result.setExpression(this.<Expression> build(ctx.exp()));

   final List<StatementContext> statements = ctx.statement();
   if (statements != null) {
     builds(whileBody.statements(), statements);
   }

   return result;
 }
 

 
 @Override
 public MethodInvocation visitInvoke(final InvokeContext ctx) {
   final MethodInvocation result = this.ast.newMethodInvocation();

   if (ctx.id1 != null) {
     result.setExpression(this.ast.newSimpleName(ctx.id1.getText()));
   }

   result.setName(this.ast.newSimpleName(ctx.id2.getText()));

   final ArgsContext args = ctx.args();
   if (args != null) {
     builds(result.arguments(), args.exp());
   }

   return result;
 }

 @Override
 public ReturnStatement visitReturnStatement(final ReturnStatementContext ctx) {
   final ReturnStatement result = this.ast.newReturnStatement();

   final ExpContext exp = ctx.exp();
   if (exp != null) {
     result.setExpression(this.<Expression> build(exp));
   }

   return result;
 }
 @Override
 public DoStatement visitDoWhileStatement(final DoWhileStatementContext ctx) {
   final DoStatement result = this.ast.newDoStatement();
   final Block dowhileBody = this.ast.newBlock();
   result.setBody(dowhileBody);

   final List<StatementContext> statements = ctx.statement();
   if (statements != null) {
     builds(dowhileBody.statements(), statements);
   }
   
   result.setExpression(this.<Expression> build(ctx.exp()));

   return result;
 }
	 
 @Override
 public ForStatement visitForStatement(final ForStatementContext ctx){
	  final ForStatement result = this.ast.newForStatement();
	  final Block forStatement = this.ast.newBlock();
	  result.setBody(forStatement);
	  
	  final ForInitContext forInit = ctx.forInit();
	    if (forInit != null) {
	    	builds(result.initializers(), forInit.assign());
	    }
	    
	  final ExpContext exp = ctx.exp();
	    if (exp != null) {
	      result.setExpression(this.<Expression> build(ctx.exp()));
	    }
	  
	  final ForUpdateContext forUpdate = ctx.forUpdate();
	    if (forUpdate != null) {
	    	builds(result.updaters(), forUpdate.incdecStatement());
	    }
	 
	  final List<StatementContext> statements = ctx.statement();
	    if (statements != null) {
	        builds(forStatement.statements(), statements);
	    }
	  
	return result;
	  
 }
 @Override
 public PostfixExpression visitIncdecStatement(final IncdecStatementContext ctx){
	  final PostfixExpression result = this.ast.newPostfixExpression();
	  result.setOperand(this.<Expression>build(ctx.lhs()));  
	  result.setOperator(ExtendedStaticJavaASTBuilder.incdecopMap.get(ctx.op.getText()));
	  
	  return result;
 }
 
 @Override
 public ExpressionStatement visitIncdec(final IncdecContext ctx){
	  
	  final PostfixExpression result = this.ast.newPostfixExpression();
	  final ExpressionStatement expressionStatement =this.ast.newExpressionStatement(result);
	  result.setOperand(this.<Expression>build(ctx.lhs())); 
	  result.setOperator(ExtendedStaticJavaASTBuilder.incdecopMap.get(ctx.op.getText()));
	  
	  return expressionStatement;
 }
 @Override
 public NumberLiteral visitIntLiteral(final IntLiteralContext ctx) {
   final NumberLiteral result = this.ast.newNumberLiteral();
   result.setToken(ctx.INT().getText());
   return result;
 }
 @Override
 public NullLiteral visitNullLiteral(final NullLiteralContext ctx) {
   return this.ast.newNullLiteral();
 }

 @Override
 public BooleanLiteral visitTrueLiteral(final TrueLiteralContext ctx) {
   return this.ast.newBooleanLiteral(true);
 }
 
 @Override
 public BooleanLiteral visitFalseLiteral(final FalseLiteralContext ctx) {
   return this.ast.newBooleanLiteral(false);
 }
 @Override
 public ParenthesizedExpression visitParenExp(final ParenExpContext ctx) {
   final ParenthesizedExpression result = this.ast
       .newParenthesizedExpression();

   result.setExpression(this.<Expression> build(ctx.exp()));

   return result;
 } 
 
 @Override
 public MethodInvocation visitInvokeExp(final InvokeExpContext ctx) {
   return this.<MethodInvocation> build(ctx.invoke());
 }
 
 @Override
 public SimpleName visitIdExp(final IdExpContext ctx) {
   return this.ast.newSimpleName(ctx.ID().getText());
 }

 
@Override
 public PrefixExpression visitUnaryExp(final UnaryExpContext ctx) {
   final PrefixExpression result = this.ast.newPrefixExpression();

   result.setOperator(ExtendedStaticJavaASTBuilder.unopMap.get(ctx.op.getText()));

   result.setOperand(this.<Expression> build(ctx.exp()));

   return result;
 }
@Override 
public ArrayAccess visitArrayAccess(final ArrayAccessContext ctx){
	  final ArrayAccess result = this.ast.newArrayAccess();
	  
	  result.setArray(this.<Expression> build(ctx.e1));
	  result.setIndex(this.<Expression> build(ctx.e2));
	  
	  return result;
}

@Override
public FieldAccess visitFieldAccess(final FieldAccessContext ctx) {
	  final FieldAccess result = this.ast.newFieldAccess();
	  
	  result.setExpression(this.<Expression> build(ctx.exp()));
	  result.setName(this.ast.newSimpleName(ctx.ID().getText()));
	  
	  return result;
}

  @Override
  public InfixExpression visitBinaryExp(final BinaryExpContext ctx) {
    final InfixExpression result = this.ast.newInfixExpression();

    result.setLeftOperand(this.<Expression> build(ctx.e1));

    result.setOperator(ExtendedStaticJavaASTBuilder.binopMap.get(ctx.op.getText()));

    result.setRightOperand(this.<Expression> build(ctx.e2));

    return result;
  }
  
  @Override
  public ClassInstanceCreation visitCreateClass(final CreateClassContext ctx){
	  final ClassInstanceCreation result = this.ast.newClassInstanceCreation();
	  result.setType(this.ast.newSimpleType(this.ast.newSimpleName(ctx.ID().getText())));
	  return result;
  }

  @Override
  public ArrayCreation visitCreateArrayInit(final CreateArrayInitContext ctx){
	  final ArrayCreation result = this.ast.newArrayCreation();
	  result.setType(this.ast.newArrayType(this.<Type> build(ctx.type())));
	  result.setInitializer(this.<ArrayInitializer>build(ctx.arrayInit()));
	  return result;
  }
  
  @Override
  public ArrayCreation visitCreateArrayExp(final CreateArrayExpContext ctx){
  	  final ArrayCreation result = this.ast.newArrayCreation();
  	  result.setType(this.ast.newArrayType(this.<Type> build(ctx.type())));
  	  final List<ExpContext> exp = Arrays.asList(ctx.exp());
  	  if(exp!=null){
  		  builds(result.dimensions(),exp);}
  	  return result;
  }
  
  @Override
  public ArrayInitializer visitArrayInit(final ArrayInitContext ctx){
	  final ArrayInitializer result = this.ast.newArrayInitializer();
	  final List<ExpContext> exp = ctx.exp();
	    if (exp != null) {
	        builds(result.expressions(), exp);
	    }
	  return result;
  }
  @Override
  public ConditionalExpression visitCondExp(final CondExpContext ctx){
	  final ConditionalExpression result = this.ast.newConditionalExpression();
	  
	  result.setExpression(this.<Expression> build(ctx.e1));
	  result.setThenExpression(this.<Expression> build(ctx.e2));
	  result.setElseExpression(this.<Expression> build(ctx.e3));
	  
	  return result;
	  
  }

  @Override
  public ExpressionStatement visitInvokeExpStatement(
     final InvokeExpStatementContext ctx) {
   return this.ast.newExpressionStatement(this.<MethodInvocation> build(ctx
       .invoke()));
 }

@Override
 public SimpleName visitAccessVar(final AccessVarContext ctx) {
  return this.ast.newSimpleName(ctx.ID().getText());
}

@Override
 public FieldAccess visitAccessField(final AccessFieldContext ctx) {
	  final FieldAccess result = this.ast.newFieldAccess();
	  
	  result.setExpression(this.<Expression> build(ctx.exp()));
	  result.setName(this.ast.newSimpleName(ctx.ID().getText()));
	  
	  return result;
}

@Override 
 public ArrayAccess visitAccessArray(final AccessArrayContext ctx){
	  final ArrayAccess result = this.ast.newArrayAccess();
	  
	  result.setArray(this.<Expression> build(ctx.e1));
	  result.setIndex(this.<Expression> build(ctx.e2));
	  
	  return result;
}

}
