package sjc.codegen.extended;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.internal.compiler.ast.IntLiteral;
import org.stringtemplate.v4.*;

import sjc.annotation.*;
import sjc.codegen.ByteCodeGenerator;
import sjc.codegen.ClassByteCodes;
import sjc.codegen.extended.ExtendedByteCodeGenerator.Visitor;
import sjc.symboltable.SymbolTable;
import sjc.symboltable.extended.ExtendedSymbolTable;
import sjc.type.Type;
import sjc.type.checker.TypeTable;
import sjc.type.checker.extended.ExtendedTypeTable;
import sjc.util.Pair;

public class CodeTranslator {
	public AST ast = AST.newAST(AST.JLS8);

	public static void main(String[] args) {
		/*
		 * ST hello = new ST("Hello, <name; separator=\", \">");
		 * hello.add("name", "World"); hello.add("name", "Urmi");
		 * System.out.println(hello.render());
		 */

		STGroup group = new STGroupFile("src/main/test.stg");
		ST st = group.getInstanceOf("binaryExp");
		st.add("exp1", "x");
		st.add("op", "+");
		st.add("exp2", "0");
		Visitor v = new Visitor(null, null);
		CodeTranslator t = new CodeTranslator();
		final InfixExpression e = t.ast.newInfixExpression();

		e.setLeftOperand(t.ast.newSimpleName("x1"));
		e.setRightOperand(t.ast.newSimpleName("x2"));
		e.setOperator(InfixExpression.Operator.PLUS);
		v.visit(e);
		System.out.println(v.result.render());

	}

	protected static class Visitor extends ASTVisitor {

		Map<ASTNode, ST> resultMap;
		public ST result = null;
		STGroup group = new STGroupFile("src/main/stringTemplates.stg");

		public @PossiblyNull String mainClassName;

		public @PossiblyNull byte[] mainClassBytes;

		protected @NonNullElements @ReadOnlyElements Map<ASTNode, Object> symbolMap;

		protected @NonNullElements @ReadOnlyElements Map<ASTNode, Type> typeMap;

		protected @NonNullElements @ReadOnlyElements Map<Object, Pair<Type, List<Type>>> methodTypeMap;

		protected @NonNull @NonNullElementsOnly List<Pair<String, Type>> localNamesTypes = new ArrayList<Pair<String, Type>>();

		protected @NonNull @NonNullElementsOnly Map<String, Integer> localIndexMap = new HashMap<String, Integer>();

		// extended
		Map<String, byte[]> otherClasses = new HashMap<String, byte[]>();

		protected @NonNullElements @ReadOnlyElements Map<String, TypeDeclaration> classMap;

		protected @NonNullElements @ReadOnlyElements Map<Pair<String, String>, FieldDeclaration> fieldMap;
		
		protected Map<String, ST> arraySizeMap;
		
		protected List<ASTNode> localVars = new ArrayList<ASTNode>();

		// extended

		protected void setResult(final ASTNode e, final ST st) {
			assert st != null;
			this.resultMap.put(e, st);
			this.result = st;
		}

		protected ST getResult() {
			final ST val = this.result;
			this.result = null;
			return val;
		}

		protected void builds(ST str, final List<ASTNode> l) {

			if (!l.isEmpty()) {
				for (ASTNode node : l) {
					node.accept(this);
					str.add("exp1", this.getResult());
				}
			}
		}
		protected ASTNode getParent(ASTNode node){
			while(!(node instanceof Assignment)){
				node = node.getParent();
			}
			node = ((Assignment) node).getLeftHandSide();
			while(true){
				if (node instanceof ArrayAccess){
					node = ((ArrayAccess)node).getArray();
				}else if (node instanceof FieldAccess){
					if (((FieldAccess)node).getExpression() != null){
						node = ((FieldAccess)node).getExpression();
					}
				}else{
					return node;
				}
			}
			
		}
		
		
		

		protected Visitor(@NonNull final ExtendedSymbolTable st, @NonNull final ExtendedTypeTable tt) {
			assert (st != null) && (tt != null);
			this.symbolMap = st.symbolMap;
			this.typeMap = tt.typeMap;
			this.methodTypeMap = tt.methodTypeMap;
			this.resultMap = new HashMap<ASTNode, ST>();
			this.arraySizeMap = new HashMap<String, ST>();
			this.classMap = st.classMap;
			this.fieldMap = st.fieldMap;
			
		}

		protected void dispose() {
			this.symbolMap = null;
			this.typeMap = null;
			this.methodTypeMap = null;
			this.localNamesTypes = null;
			this.localIndexMap = null;
			this.mainClassName = null;
			this.mainClassBytes = null;
			this.arraySizeMap = null;
		}

		public boolean visit(InfixExpression node) {
			ST st;
			if(node.getOperator() == InfixExpression.Operator.RIGHT_SHIFT_UNSIGNED){
				st = group.getInstanceOf("unsignedRightShift");
			}else if(node.getOperator() == InfixExpression.Operator.LEFT_SHIFT){
				st = group.getInstanceOf("ckeckLeftShift");
			}else if (node.getOperator() == InfixExpression.Operator.DIVIDE){
				st = group.getInstanceOf("division");
			}else{
				st = group.getInstanceOf("binaryExp");
			}
			node.getLeftOperand().accept(this);
			st.add("exp1", this.getResult());
			st.add("op", node.getOperator().toString());
			node.getRightOperand().accept(this);
			st.add("exp2", this.getResult());
			setResult(node, st);
			return false;
		}

		public boolean visit(PrefixExpression node) {
			ST st = group.getInstanceOf("unaryExp");
			st.add("op", node.getOperator().toString());
			node.getOperand().accept(this);
			st.add("exp1", this.getResult());
			setResult(node, st);
			return false;

		}

		public boolean visit(ConditionalExpression node) {
			ST st = group.getInstanceOf("condExp");
			node.getExpression().accept(this);
			st.add("exp1", this.getResult());
			node.getThenExpression().accept(this);
			st.add("exp2", this.getResult());
			node.getElseExpression().accept(this);
			st.add("exp3", this.getResult());
			setResult(node, st);
			return false;

		}

		public boolean visit(ArrayAccess node) {
			ST st;
			if ((node.getParent() instanceof Assignment && ((Assignment)node.getParent()).getLeftHandSide() == node)
					|| node.getParent() instanceof PostfixExpression){
				st = group.getInstanceOf("lhsArrayAccess");
			}else{
				st = group.getInstanceOf("arrayAccess");
				ST size = arraySizeMap.get(node.getArray().toString());
				if (size == null)
					st.add("size1", -1);
				else st.add("size1", size);
				}
			node.getArray().accept(this);
			st.add("exp1", this.getResult());
			node.getIndex().accept(this);
			st.add("index", this.getResult());
			
			setResult(node, st);
			return false;
		}

		public boolean visit(ArrayCreation node) {
			if (node.dimensions().isEmpty()) {
				ST st = group.getInstanceOf("ArrayWElementNewExpr");
				node.getType().getElementType().accept(this);
				st.add("type1", this.getResult());
				node.getInitializer().accept(this);
				st.add("arrayInit1", this.getResult());
				ST size = group.getInstanceOf("id");
				size.add("v", node.getInitializer().expressions().size()+1);
				st.add("size1", size);
				getParent(node).accept(this);
				ST parent = this.getResult();
				st.add("var1", parent);
				arraySizeMap.put(parent.render(), size);
				
				setResult(node, st);
			} else {
				ST st = group.getInstanceOf("ArrayWLengthNewExpr");
				node.getType().getElementType().accept(this);
				st.add("type1", this.getResult());
				ST size = group.getInstanceOf("id");
				if (node.dimensions().get(0) instanceof Expression){
					((Expression)node.dimensions().get(0)).accept(this);
					size = this.getResult();
					st.add("size1", size);
				}
				getParent(node).accept(this);
				ST parent = this.getResult();
				st.add("var1", parent);
				arraySizeMap.put(parent.render(), size);
				
				setResult(node, st);
			}
			return false;
		}

		public boolean visit(ClassInstanceCreation node) {
			ST st = group.getInstanceOf("ClassInstanceNewExpr");
			st.add("id1", node.getType().toString());
			getParent(node).accept(this);
			st.add("var1", this.getResult());
			setResult(node, st);
			return false;

		}

		public boolean visit(FieldDeclaration node) {
			ST st = group.getInstanceOf("fieldDeclaration");
			node.getType().accept(this);
			st.add("type1", this.getResult());
			ST name = group.getInstanceOf("args");
			builds(name, node.fragments());
			st.add("id1", name);
			setResult(node, st);
			return false;
		}

		public boolean visit(FieldAccess node) {
			ST st;
			if ((node.getParent() instanceof Assignment && ((Assignment)node.getParent()).getLeftHandSide() == node)
					||node.getParent() instanceof PostfixExpression
					||node.getParent() instanceof FieldAccess){
				st = group.getInstanceOf("lhsField");
			}else{
				st = group.getInstanceOf("fieldAccess");
			}
			if (node.getExpression() != null){
				node.getExpression().accept(this);
				st.add("exp1", this.getResult());
			}
			node.getName().accept(this);
			st.add("id1", this.getResult());
			setResult(node, st);
			return false;

		}

		public boolean visit(Assignment node) {
			ST st = group.getInstanceOf("assign");
			node.getLeftHandSide().accept(this);
			ST lhs = this.getResult();
			if (node.getLeftHandSide() instanceof ArrayAccess){
				ArrayAccess array = ((ArrayAccess)node.getLeftHandSide());
				ST check = group.getInstanceOf("lhsArrayCheck");
				array.getArray().accept(this);
				check.add("exp1",this.getResult());
				array.getIndex().accept(this);
				check.add("index1",this.getResult());
				ST size = arraySizeMap.get(array.getArray().toString());
				if (size == null)
					check.add("size1", -1);
				else check.add("size1", size);
				st.add("check1", check);
			}/*else if (node.getLeftHandSide() instanceof FieldAccess){
				FieldAccess field = ((FieldAccess)node.getLeftHandSide());
				ST check = group.getInstanceOf("lhsFieldCheck");
				field.getExpression().accept(this);
				check.add("exp1",this.getResult());
				st.add("check1", check);
			}*/
			st.add("lhs1", lhs);
			node.getRightHandSide().accept(this);
			st.add("exp1", this.getResult());
			setResult(node, st);
			return false;

		}

		public boolean visit(ParenthesizedExpression node) {
			ST st = group.getInstanceOf("parenExp");
			if (node.getExpression() != null){
				node.getExpression().accept(this);
				st.add("exp1", this.getResult());
			}
			setResult(node, st);
			return false;
		}

		public boolean visit(NumberLiteral node) {
			ST st = group.getInstanceOf("id");
			st.add("v", node.getToken());
			setResult(node, st);
			return false;
		}

		public boolean visit(BooleanLiteral node) {
			ST st = group.getInstanceOf("id");
			st.add("v", node.booleanValue());
			setResult(node, st);
			return false;
		}

		public boolean visit(NullLiteral node) {
			ST st = group.getInstanceOf("id");
			st.add("v", "NULL");
			setResult(node, st);
			return false;
		}

		public boolean visit(SimpleName node) { // extend
			ST st = group.getInstanceOf("id");
			st.add("v", node.getIdentifier());
			setResult(node, st);
			return false;
		}
		
		public boolean visit(SimpleType node){
			node.getName().accept(this);
			ST st = group.getInstanceOf("classType");
			st.add("type1", this.getResult());
			setResult(node, st);
			return false;
		}

		public boolean visit(WhileStatement node) {
			ST st = group.getInstanceOf("whileStatement");
			node.getExpression().accept(this);
			st.add("exp1", this.getResult());
			node.getBody().accept(this);
			st.add("statement", this.getResult());
			setResult(node, st);
			return false;

		}

		public boolean visit(ArrayInitializer node) {
			ST st = group.getInstanceOf("arrayInit");
			builds(st, node.expressions());
			setResult(node, st);
			return false;
		}

		public boolean visit(PostfixExpression node) {
			if (node.getOperator() == PostfixExpression.Operator.INCREMENT) {
				ST st = group.getInstanceOf("increment");
				node.getOperand().accept(this);
				st.add("lhs1", this.getResult());
				setResult(node, st);

			} else if (node.getOperator() == PostfixExpression.Operator.DECREMENT) {
				ST st = group.getInstanceOf("decrement");
				node.getOperand().accept(this);
				st.add("lhs1", this.getResult());
				setResult(node, st);
			}

			return false;

		}

		public boolean visit(IfStatement node) {
			ST st = group.getInstanceOf("ifStatement");
			node.getExpression().accept(this);
			st.add("exp1", this.getResult());
			node.getThenStatement().accept(this);
			st.add("then1", this.getResult());
			node.getElseStatement().accept(this);
			st.add("else1", this.getResult());
			setResult(node, st);
			return false;

		}

		public boolean visit(DoStatement node) {
			ST st = group.getInstanceOf("doWhileStatement");
			node.getBody().accept(this);
			st.add("statement", this.getResult());
			node.getExpression().accept(this);
			st.add("exp1", this.getResult());
			setResult(node, st);
			return false;

		}

		protected void setAllNull(ST st){
			for(int i = 0; i < localVars.size(); i++){
				ST nll = group.getInstanceOf("setToNull");
				nll.add("var1", localVars.get(i));
				st.add("vars1", nll);
			}
			localVars.clear();
		}
		public boolean visit(ReturnStatement node) {
			ST st;
			if (node.getExpression() != null){
				Type t = this.typeMap.get(node.getExpression());
				if (t.name == "int"){
					st = group.getInstanceOf("primitiveReturnStatement");
				}else{
					st = group.getInstanceOf("returnStatement");
				}
			}else{st = group.getInstanceOf("primitiveReturnStatement");}
			if (node.getExpression() != null) {
				node.getExpression().accept(this);
				st.add("exp1", this.getResult());
			}
			setAllNull(st);
			setResult(node, st);
			return false;

		}

		protected Pair<MethodDeclaration, List<ASTNode>> getMainMethod(List<ASTNode> nodeList) {
			if (!nodeList.isEmpty()) {
				for (ASTNode node : nodeList) {
					if (node instanceof MethodDeclaration) {
						final MethodDeclaration md = (MethodDeclaration) node;
						if (md.getName().toString().equals("main") && md.getModifiers() > 1) {
							nodeList.remove(node);
							return new Pair<MethodDeclaration, List<ASTNode>>(md, nodeList);
						}
					}
				}
			}
			return null;
		}

		protected org.eclipse.jdt.core.dom.Type getType(SimpleName node) {
			final ASTNode parent = node.getParent();
			if ((parent instanceof Expression) || (parent instanceof Statement)) {
				final Object o = this.symbolMap.get(node);
				if (o instanceof FieldDeclaration) {
					final FieldDeclaration fd = (FieldDeclaration) o;
					return fd.getType();
				} else if (o instanceof SingleVariableDeclaration) {
					final SingleVariableDeclaration svd = (SingleVariableDeclaration) o;
					return svd.getType();
				} else if (o instanceof VariableDeclarationStatement) {
					final VariableDeclarationStatement vds = (VariableDeclarationStatement) o;
					return vds.getType();
				}
			} else {
				throw new Error("Unexpected SimpleName: \'" + node + "\'");
			}
			return null; // scary and dangrous

		}
		
		

		public boolean visit(ArrayType node) {
			ST st = group.getInstanceOf("arrayType");
			node.getElementType().accept(this);
			st.add("type1", this.getResult());
			setResult(node, st);
			return false;
		}

		public boolean visit(PrimitiveType node) {
			if (node.getPrimitiveTypeCode() == PrimitiveType.BOOLEAN) {
				ST st = group.getInstanceOf("id");
				st.add("v", "bool");
				setResult(node, st);
				return false;
			}
			ST st = group.getInstanceOf("id");
			st.add("v", node.getPrimitiveTypeCode());
			setResult(node, st);
			return false;
		}

		public boolean visit(ExpressionStatement node) {
			if (node.getExpression() != null) {
				final Expression e = node.getExpression();
				if (e instanceof Assignment || e instanceof MethodInvocation || e instanceof PostfixExpression) {
					ST st = group.getInstanceOf("assignStatement");
					e.accept(this);
					st.add("assign1", getResult());
					setResult(node, st);
				} else {
					return true;
				}
			}
			return false;
		}

		public boolean visit(VariableDeclarationStatement node) {
			ST st = group.getInstanceOf("localDeclaration");
			ST fragments = group.getInstanceOf("args");
			node.getType().accept(this);
			st.add("type", this.getResult()); //change other type declarations
			builds(fragments, node.fragments());
			if (!node.getType().isPrimitiveType()){
				localVars.addAll(node.fragments());
			}
			st.add("id1", fragments);
			setResult(node, st);
			return false;
		}

		public boolean visit(ForStatement node) {
			ST inits = group.getInstanceOf("args"), updates = group.getInstanceOf("args");
			builds(inits, node.initializers());
			builds(updates, node.updaters());
			ST st = group.getInstanceOf("forStatement");
			st.add("init", inits);
			if (node.getExpression() != null) {
				node.getExpression().accept(this);
				st.add("exp1", this.getResult());
			}
			st.add("update", updates);
			node.getBody().accept(this);
			st.add("statement", this.getResult());
			setResult(node, st);
			return false;
		}

		public boolean visit(TypeDeclaration node) {
			if (node.getModifiers() > 0) {
				ST st = group.getInstanceOf("classDeclaration");
				ST body = group.getInstanceOf("block");
				final Pair<MethodDeclaration, List<ASTNode>> p = getMainMethod(node.bodyDeclarations());
				builds(body, p.second);
				st.add("body", body);
				p.first.accept(this);
				st.add("mainMethod", getResult());
				setResult(node, st);
			} else {
				ST st = group.getInstanceOf("simpleClassDeclaration");
				st.add("id1", node.getName().getIdentifier());
				ST fields = group.getInstanceOf("block");
				builds(fields, node.bodyDeclarations());
				st.add("fields1", fields);
				setResult(node, st);
			}
			return false;
		}

		public boolean visit(CompilationUnit node) {
			// ST st = group.getInstanceOf("args");
			ST stClass = group.getInstanceOf("compilationUnit");
			if (!node.types().isEmpty()) {
				for (Object n : node.types()) {
					if (n instanceof TypeDeclaration) {
						final TypeDeclaration class1 = (TypeDeclaration) n;
						class1.accept(this);
						if (class1.getModifiers() > 0) {
							stClass.add("mainClass", getResult());
						} else {
							stClass.add("simpleClass", getResult());
						}
					}
				}
			}
			setResult(node, stClass);
			return false;
		}

		public boolean visit(Block node) {
			ST body = group.getInstanceOf("block");
			builds(body, node.statements());
			setResult(node, body);
			return false;
		}

		public boolean visit(MethodDeclaration node) {
			if (node.getModifiers() > 1 && node.getName().getIdentifier() == "main") {
				ST st = group.getInstanceOf("mainMethodDeclaration");
				if (!node.parameters().isEmpty()) {
					Object o = node.parameters().get(0);
					if (o instanceof SingleVariableDeclaration) {
						st.add("id1", ((SingleVariableDeclaration) o).getName().getIdentifier());
						node.getBody().accept(this);
						st.add("methodBody1", getResult());
					}
				}
				setResult(node, st);
			} else {
				ST st = group.getInstanceOf("methodDeclaration");
				node.getReturnType2().accept(this);
				st.add("returnType", this.getResult());
				st.add("id1", node.getName().getIdentifier());
				ST params = group.getInstanceOf("args");
				builds(params, node.parameters());
				st.add("param1", params);
				node.getBody().accept(this);
				st.add("methodBody1", getResult());
				//String className = ((TypeDeclaration) node.getParent()).getName().getIdentifier();
				//st.add("className", className);
				setResult(node, st);
			}
			return false;
		}

		public boolean visit(SingleVariableDeclaration node) {
			ST st = group.getInstanceOf("param");
			node.getType().accept(this);
			st.add("type1", getResult());
			node.getName().accept(this);
			st.add("id1", this.getResult());
			setResult(node, st);
			return false;
		}

		public boolean visit(MethodInvocation node) {
			ST st = group.getInstanceOf("invExp2");
			st.add("id2", node.getName().getIdentifier());
			ST args = group.getInstanceOf("args");
			builds(args, node.arguments());
			st.add("args", args);
			/*if (node.getExpression() != null) {
				ST st1 = group.getInstanceOf("invExp1");
				node.getExpression().accept(this);
				st1.add("id1", getResult());
				st1.add("invoke", st);
				setResult(node, st1);
				return false;
			}*/ 			//Dangerous way of getting rid of StaticJavaLib
			setResult(node, st);
			return false;
		}

	}

	/*
	 * public static @NonNull ClassByteCodes generate(
	 * 
	 * @NonNull final CompilationUnit cu, @NonNull final SymbolTable st,
	 * 
	 * @NonNull final TypeTable tt) throws ByteCodeGenerator.Error { assert (cu
	 * != null) && (st != null) && (tt != null);
	 * 
	 * final Visitor v = new Visitor(st, tt); cu.accept(v); final ClassByteCodes
	 * result = new ClassByteCodes(v.mainClassName,v.mainClassBytes);
	 * v.dispose(); return result; }
	 */
	public static @NonNull ST generate(@NonNull final CompilationUnit cu, @NonNull final ExtendedSymbolTable est,
			@NonNull final ExtendedTypeTable ett) throws ByteCodeGenerator.Error {
		assert (cu != null) && (est != null) && (ett != null);

		final Visitor v = new Visitor(est, ett);
		cu.accept(v);
		// final ExtendedClassByteCodes result = new ExtendedClassByteCodes(
		// v.mainClassName, v.mainClassBytes, v.otherClasses);
		final ST result = v.result;
		v.dispose();
		return result;
	}

	protected CodeTranslator() {
	}

}