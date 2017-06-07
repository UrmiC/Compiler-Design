package sjc.type.checker.extended;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import sjc.annotation.NonNull;
import sjc.annotation.NonNullElements;
import sjc.annotation.ReadOnlyElements;
import sjc.symboltable.SymbolTable;
import sjc.symboltable.extended.ExtendedSymbolTable;
import sjc.type.ArrayType;
import sjc.type.BaseType;
import sjc.type.ClassType;
import sjc.type.NonPrimitiveType;
import sjc.type.NullType;
import sjc.type.Type;
import sjc.type.TypeFactory;
import sjc.type.checker.TypeChecker;
//import sjc.type.checker.TypeChecker.Error;
import sjc.util.Pair;

/**
 * This class is used to type check a ExtendedStaticJava {@link CompilationUnit} with a
 * given {@link SymbolTable}.
 * 
 * @author <a href="mailto:myname@ksu.edu">MyName</a>
 */
public class ExtendedTypeChecker extends TypeChecker {
  /**
   * The visitor for {@link ASTNode} to type check an ExtendedStaticJava
   * {@link CompilationUnit}.
   * 
   * @author <a href="mailto:robby@cis.ksu.edu">Robby</a>
   */
  protected static class Visitor extends TypeChecker.Visitor {
    protected @NonNullElements
    @ReadOnlyElements
    Map<String, TypeDeclaration> classMap;

    protected @NonNullElements
    @ReadOnlyElements
    Map<Pair<String, String>, FieldDeclaration> fieldMap;

    protected Visitor(final TypeFactory tf, final ExtendedSymbolTable est) {
      super(tf, est);
      this.classMap = est.classMap;
      this.fieldMap = est.fieldMap;
      for (final Pair<String, String> p : est.fieldMap.keySet()) {
        final FieldDeclaration fd = est.fieldMap.get(p);
        final Type t = convertType(fd, fd.getType());
        final String className = p.first;
        final String fieldName = p.second;
        final ClassType ct = tf.getClassType(className);
        ct.fieldTypeMap.put(fieldName, t);
      }
    }

    // TODO: Fill in visit methods here.
    
    @Override
    public boolean visit(final Assignment node) {
      node.getLeftHandSide().accept(this);
      final Type lhsType = getResult();
      node.getRightHandSide().accept(this);
      final Type rhsType = getResult();
      if (lhsType != rhsType && !((lhsType instanceof NonPrimitiveType) && (rhsType instanceof NullType))) {
        throw new Error(node, "Type mismatch in \"" + node + "\": " + lhsType
            + " = " + rhsType);
      }
      return false;
    }
    @Override
    public boolean visit(final ArrayCreation node) {
      
    	final Type arrayType = convertType(node,node.getType());
    	
    	if(!(node.dimensions().isEmpty())){
    	List dimlist = node.dimensions();
    	final Expression expdimen = (Expression) dimlist.get(0);
    	expdimen.accept(this);
    	}
    	
    	ArrayInitializer arrayinit = node.getInitializer();
    	 if(!(arrayinit == null)){
    	 for (final Object o : arrayinit.expressions()) {
    	        final Expression exp = (Expression) o;
    	        exp.accept(this);
    	      }
    	 }
    	setResult(node, arrayType);
      return false;
    }
    @Override
    public boolean visit(final ClassInstanceCreation node) {
    	final Type classType = convertType(node,node.getType());
    	
    	String classname = ((SimpleType)node.getType()).getName().getFullyQualifiedName();
    	setResult(node, classType);
    	this.symbolMap.put(node, classMap.get(classname));
      return false;
    }
    public boolean visit(final NullLiteral node) {
    	setResult(node, this.tf.Null);
    	return false;
    }
    

    
    @Override
    public boolean visit(final ConditionalExpression node) {
      
    	node.getExpression().accept(this);
    	final Type condexpType = getResult();
    	
    	if (condexpType != this.tf.Boolean) {
            throw new Error(node,
                "Expecting a boolean type expression as the conditional expression of "
                + " in \"" + node + "\"");
          }
    	
    	node.getThenExpression().accept(this);
    	final Type condthenType = getResult();
    	
    	node.getElseExpression().accept(this);
    	final Type condelseType = getResult();
    	
    	
    	if((condthenType instanceof NullType) && (condelseType instanceof NonPrimitiveType))
        {
      	  setResult(node, condelseType);
      	  return false;
        }
        
        if((condelseType instanceof NullType) && (condthenType instanceof NonPrimitiveType))
        {
      	  setResult(node, condthenType);
      	  return false;
        }
    	
    	if (condthenType != condelseType) {
            throw new Error(node, "Type mismatch in conditional expression \"" + node + "\": " + condthenType
                + " :? " + condelseType);
          }
    	
      
    	 
    	 setResult(node, condelseType);
      return false;
    }

    @Override
    protected Type convertType(final ASTNode node,
        final org.eclipse.jdt.core.dom.Type t) {
      if (t instanceof SimpleType) {
        final SimpleType st = (SimpleType) t;
        final String name = st.getName().getFullyQualifiedName();
        if (this.classMap.containsKey(name)) {
          return this.tf.getClassType(name);
        }
      }
      return super.convertType(node, t);
    }
    
    @Override
    public boolean visit(final ReturnStatement node) {
      final Expression e = node.getExpression();
      if ((this.methodReturnType == this.tf.Void) && (e != null)) {
        throw new Error(node, "Unexpected return's expression in \"" + node
            + "\"");
      } else if ((this.methodReturnType != this.tf.Void) && (e == null)) {
        throw new Error(node, "Expecting a return's expression in \"" + node
            + "\"");
      } else if ((this.methodReturnType != this.tf.Void) && (e != null)) {
        e.accept(this);
        final Type t = getResult();
        if (t != this.methodReturnType && ( !(t instanceof NullType) && !(methodReturnType instanceof NonPrimitiveType))) {
          throw new Error(node, "Expecting " + this.methodReturnType.name
              + " return expression in \"" + node + "\"");
        }
      }
      return false;
    }

    
    @Override
    public boolean visit(final InfixExpression node) {
      node.getLeftOperand().accept(this);
      final Type lhsType = getResult();
      node.getRightOperand().accept(this);
      final Type rhsType = getResult();
      final InfixExpression.Operator op = node.getOperator();
      if ((op == InfixExpression.Operator.TIMES)
          || (op == InfixExpression.Operator.DIVIDE)
          || (op == InfixExpression.Operator.REMAINDER)
          || (op == InfixExpression.Operator.PLUS)
          || (op == InfixExpression.Operator.MINUS)) {
        if (lhsType != this.tf.Int) {
          throw new Error(node,
              "Expecting an int type expression on the left-hand operand of \""
                  + op + "\" in \"" + node + "\"");
        }
        if (rhsType != this.tf.Int) {
          throw new Error(node,
              "Expecting an int type expression on the right-hand operand of \""
                  + op + "\" in \"" + node + "\"");
        }
        setResult(node, this.tf.Int);
      }else if ((op == InfixExpression.Operator.RIGHT_SHIFT_SIGNED)
              || (op == InfixExpression.Operator.LEFT_SHIFT)
              || (op == InfixExpression.Operator.RIGHT_SHIFT_UNSIGNED)) {
            if (lhsType != this.tf.Int) {
              throw new Error(node,
                  "Expecting an int type expression on the left-hand operand of \""
                      + op + "\" in \"" + node + "\"");
            }
            if (rhsType != this.tf.Int && !(rhsType instanceof ArrayType)) {
              throw new Error(node,
                  "Expecting an int type expression on the right-hand operand of \""
                      + op + "\" in \"" + node + "\"");
            }
            setResult(node, this.tf.Int); 
      }
      else if ((op == InfixExpression.Operator.LESS)
          || (op == InfixExpression.Operator.GREATER)
          || (op == InfixExpression.Operator.LESS_EQUALS)
          || (op == InfixExpression.Operator.GREATER_EQUALS)) {
        if (lhsType != this.tf.Int) {
          throw new Error(node,
              "Expecting an int type expression on the left-hand operand of \""
                  + op + "\" in \"" + node + "\"");
        }
        if (rhsType != this.tf.Int) {
          throw new Error(node,
              "Expecting an int type expression on the right-hand operand of \""
                  + op + "\" in \"" + node + "\"");
        }
        setResult(node, this.tf.Boolean);
      } else if ((op == InfixExpression.Operator.CONDITIONAL_AND)
          || (op == InfixExpression.Operator.CONDITIONAL_OR)) {
        if (lhsType != this.tf.Boolean) {
          throw new Error(node,
              "Expecting a boolean type expression on the left-hand operand of \""
                  + op + "\" in \"" + node + "\"");
        }
        if (rhsType != this.tf.Boolean) {
          throw new Error(node,
              "Expecting a boolean type expression on the right-hand operand of \""
                  + op + "\" in \"" + node + "\"");
        }
        setResult(node, this.tf.Boolean);
      } else if ((op == InfixExpression.Operator.EQUALS)
          || (op == InfixExpression.Operator.NOT_EQUALS)) {
        if (lhsType != rhsType && !((lhsType instanceof NullType && rhsType instanceof NonPrimitiveType) || (rhsType instanceof NullType && lhsType instanceof NonPrimitiveType))) {
          throw new Error(node, "Type mismatch in \"" + node + "\": " + lhsType
              + " " + op + " " + rhsType);
        }
        setResult(node, this.tf.Boolean);
      } else {
        throw new Error(node, "Unexpected InfixExpression: \'" + node + "\'");
      }
      return false;
    }
    
    @Override
    public boolean visit(final ArrayAccess node) {
      
    	node.getArray().accept(this);
    	final ArrayType arrayAccessType = (ArrayType)getResult();
    	final BaseType arrayAccessBaseType =arrayAccessType.baseType;
    	
    	node.getIndex().accept(this);
    	final Type arrayindexType = getResult();
    	
    	 if (arrayindexType != this.tf.Int) {
             throw new Error(node,
                 "Expecting an Int Type index for array  in \"" + node);
           }
      
    	 setResult(node, arrayAccessBaseType);
      return false;
    }
    
    @Override
    public boolean visit(final FieldAccess node) {
    
    	node.getExpression().accept(this);
    	ClassType classType = (ClassType) getResult();
    
    	String className = classType.name;
    	if (!(classMap.containsKey(className))) {
            throw new Error(node,
                "Class not yet defined \"" + node + "\"" + className);
          }
    	
      
      String fieldName = node.getName().getIdentifier();
      Type fieldType = classType.fieldTypeMap.get(fieldName);
      if(fieldType == null)
      {
    	  throw new Error(node, 
    			  "Field not yet declared in node \"" + node + "\"");
      }
      else
      {
    	  symbolMap.put(node, fieldMap.get(new Pair(className, fieldName)));
    	  setResult(node, fieldType);
    	  return false;
      }
    	
    }
    
    @Override
    public boolean visit(final ExpressionStatement node) {
      final Expression e = node.getExpression();
      e.accept(this);
      if (e instanceof Assignment) {
        // assignment should not have a resulting type.
        assert getResult() == null;
      } else if (node.getExpression() instanceof MethodInvocation) {
        // method invocation's result can be any type (including void)
        // so we can ignore it.
        getResult();
      }
      else if (node.getExpression() instanceof PostfixExpression) {
    	  assert getResult() == null;
        } 
      else {
        throw new Error(node, "Unexpected SimpleName: \'" + node + "\'");
      }
      return false;
    }
    
    @Override
    public boolean visit(final PostfixExpression node) {
      
    	node.getOperand().accept(this);
    	final Type operandType = getResult();
      
    	final PostfixExpression.Operator op = node.getOperator();
      if ((op == PostfixExpression.Operator.INCREMENT)
          || (op == PostfixExpression.Operator.DECREMENT)) {
        if (operandType != this.tf.Int) {
          throw new Error(node,
              "Expecting an int type expression as the operand of \""
                  + op + "\" in \"" + node + "\"");
        }
      } 
      else {
        throw new Error(node, "Unexpected PostfixExpression: \'" + node + "\'");
      }
      return false;
    }
    
    
    @Override
    public boolean visit(final PrefixExpression node) {
      node.getOperand().accept(this);
      final Type t = getResult();
      final PrefixExpression.Operator op = node.getOperator();
      if ((op == PrefixExpression.Operator.PLUS)
          || (op == PrefixExpression.Operator.MINUS)
          || (op == PrefixExpression.Operator.COMPLEMENT)) {
        if (t != this.tf.Int) {
          throw new Error(node,
              "Expecting an int type expression as the operand of \"" + op
                  + "\" in \"" + node + "\"");
        }
        setResult(node, this.tf.Int);
      } else if (op == PrefixExpression.Operator.NOT) {
        if (t != this.tf.Boolean) {
          throw new Error(node,
              "Expecting a boolean type expression as the operand of \"" + op
                  + "\" in \"" + node + "\"");
        }
        setResult(node, this.tf.Boolean);
      } else {
        throw new Error(node, "Unexpected PrefixExpression: \'" + node + "\'");
      }
      return false;
    }
    
    

    @Override
    protected void dispose() {
      super.dispose();

      this.classMap = null;
      this.fieldMap = null;
    }

    @Override
    protected void typeCheckMethodInvocation(final MethodInvocation node,
        final String className, final String methodName, final Type[] argTypes,
        final Method m) {
      @SuppressWarnings("rawtypes")
      final Class[] paramTypeClasses = m.getParameterTypes();
      final int numOfParams = paramTypeClasses.length;
      if (argTypes.length != numOfParams) {
        throw new Error(node, "Wrong number of arguments to invoke method \""
            + methodName + "\" in \"" + node + "\"");
      }
      final List<Type> paramTypes = new ArrayList<Type>();
      for (int i = 0; i < numOfParams; i++) {
        final Type t = convertType(node, paramTypeClasses[i]);
        if ((argTypes[i] instanceof NullType)
            && (t instanceof NonPrimitiveType)) {
          // OK
        } else if (t != argTypes[i]) {
          throw new Error(node, "Type mismatch the " + i + " argument in \""
              + node + "\"");
        }
        paramTypes.add(t);
      }
      final Type returnType = convertType(node, m.getReturnType());
      if (!this.resultMethodTypeMap.containsKey(m)) {
        this.resultMethodTypeMap.put(m, new Pair<Type, List<Type>>(returnType,
            paramTypes));
      }
      setResult(node, returnType);
    }

    @Override
    protected void typeCheckMethodInvocation(final MethodInvocation node,
        final String className, final String methodName, final Type[] argTypes,
        final MethodDeclaration md) {
      final int numOfParams = md.parameters().size();
      if (argTypes.length != numOfParams) {
        throw new Error(node, "Wrong number of arguments to invoke method \""
            + methodName + "\" in \"" + node + "\"");
      }
      for (int i = 0; i < numOfParams; i++) {
        final Type t = convertType(node, ((SingleVariableDeclaration) md
            .parameters().get(i)).getType());
        if ((argTypes[i] instanceof NullType)
            && (t instanceof NonPrimitiveType)) {
          // OK
        } else if (t != argTypes[i]) {
          throw new Error(node, "Type mismatch the " + i + " argument in \""
              + node + "\"");
        }
      }
      final Type returnType = convertType(node, md.getReturnType2());
      setResult(node, returnType);
    }
  }

  /**
   * Type checks an ExtendedStaticJava {@link CompilationUnit} with the given
   * {@link ExtendedSymbolTable} and the given {@link TypeFactory}. It also
   * resolves {@link MethodInvocation} of library call (and put its mapping in
   * the {@link ExtendedSymbolTable}).
   * 
   * @param tf
   *          The {@link TypeFactory}.
   * @param cu
   *          The StaticJava {@link CompilationUnit}.
   * @param symbolTable
   *          The {@link ExtendedSymbolTable} of the {@link CompilationUnit}
   * @return The {@link ExtendedTypeTable}.
   * @throws TypeChecker.Error
   *           If the type checker encounter type error in the
   *           {@link CompilationUnit}.
   */
  public static @NonNull
  ExtendedTypeTable check(@NonNull final TypeFactory tf,
      @NonNull final CompilationUnit cu,
      @NonNull final ExtendedSymbolTable symbolTable) throws TypeChecker.Error {
    assert (tf != null) && (cu != null) && (symbolTable != null);

    final Visitor v = new Visitor(tf, symbolTable);
    cu.accept(v);
    final ExtendedTypeTable result = new ExtendedTypeTable(v.resultTypeMap,
        v.resultMethodTypeMap);
    v.dispose();
    return result;
  }

  /**
   * Declared as protected to disallow creation of this object outside from the
   * methods of this class.
   */
  protected ExtendedTypeChecker() {
  }
}
