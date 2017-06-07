//package sjc.type.checker;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
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

import sjc.annotation.NonNullElements;
import sjc.type.Type;
import sjc.type.checker.TypeChecker.Error;
import sjc.util.Pair;

public class test{
	public AST ast = AST.newAST(AST.JLS8);
	 
	 
    public static void main(String[] args) {
        /*ST hello = new ST("Hello, <name; separator=\", \">");
        hello.add("name", "World");
        hello.add("name", "Urmi");
        ST hello1 = new ST("How is the <name; separator=\", \">");
        hello1.add("name", "World");
        hello.add("name", hello1);
        System.out.println(hello.render());*/
    	
    	STGroup group = new STGroupFile("resources/test.stg");
    	ST st = group.getInstanceOf("binaryExp");
    	st.add("exp1", "x");
    	st.add("op", "+");
    	st.add("exp2", "0");
    	Visitor v = new Visitor();
    	test t = new test();
    	final InfixExpression e = t.ast.newInfixExpression();
    	
    	e.setLeftOperand(t.ast.newSimpleName("x1"));
    	e.setRightOperand(t.ast.newSimpleName("x2"));
    	e.setOperator(InfixExpression.Operator.PLUS);    	
    	v.visit(e);
    	System.out.println(v.result.render());
    	
    	
    }
    
    
    
    
    protected static class Visitor extends ASTVisitor {
    	protected @NonNullElements
        Map<ASTNode, Object> symbolMap;
    	Map<ASTNode, ST> resultMap;
   	 	public ST result = null; 
   	 	STGroup group = new STGroupFile("resources/test.stg");
   	 	protected Visitor(){
   	 		this.resultMap = new HashMap<ASTNode, ST>();
   	 	}
   	 
    	protected void setResult(final ASTNode e, final ST st) {
            assert st != null;
            this.resultMap.put(e, st);
            this.result = st;
          }
        protected ST getResult() {
            final ST result = this.result;
            this.result = null;
            return result;
          }
        protected void builds(ST str, final List<ASTNode> l){    		
    		if (!l.isEmpty()){
    			for (ASTNode node : l) {
					node.accept(this);
					str.add("exp1", this.getResult());
				}
    		}
    	}
        protected Pair<MethodDeclaration, List<ASTNode>> getMainMethod(List<ASTNode> nodeList){
        	if (!nodeList.isEmpty()){
        		for (ASTNode node : nodeList) {
					if (node instanceof MethodDeclaration){
						final MethodDeclaration md  = (MethodDeclaration) node;
						if(md.getName().toString() == "main" && md.getModifiers() > 1){
							nodeList.remove(node);
							return new Pair<MethodDeclaration, List<ASTNode>>(md, nodeList);
						}
					}
				}
        	}
        	return null;
        }
    	protected org.eclipse.jdt.core.dom.Type getType(SimpleName node){
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
    		}else{
    			throw new Error(node, "Unexpected SimpleName: \'" + node + "\'");
    		}
    		return null; //scary and dangrous
    		
    	}
        public boolean visit(InfixExpression node){
    		
        	ST st = group.getInstanceOf("binaryExp");
        	node.getLeftOperand().accept(this);
        	st.add("exp1", this.getResult());
        	st.add("op", node.getOperator().toString());
        	node.getRightOperand().accept(this);
        	st.add("exp2", this.getResult());
        	setResult(node, st);        	
    		return false;
    	}
        public boolean visit(ArrayType node){
        	ST st = group.getInstanceOf("arrayType");
        	st.add("type1", node.getElementType().toString());
        	setResult(node, st);
        	return false;
        }
        public boolean visit(PrimitiveType node){
        	if (node.getPrimitiveTypeCode() == PrimitiveType.VOID){
        		ST st = group.getInstanceOf("id");
        		st.add("v", "void");
        		setResult(node, st);
        		return false;
        	}
        	return true;
        }
    	public boolean visit(NumberLiteral node){
    		ST st = group.getInstanceOf("id");
    		st.add("v", node.getToken());
    		setResult(node, st);
    		return false;
    	}
    	public boolean visit(BooleanLiteral node){
    		ST st = group.getInstanceOf("id");
    		st.add("v", node.booleanValue());
    		setResult(node, st);
    		return false;
    	}
    	public boolean visit(NullLiteral node){
    		ST st = group.getInstanceOf("id");
    		st.add("v", "null");
    		setResult(node, st);
    		return false;
    	}
    	public boolean visit(SimpleName node){ 				//extend
    		ST st = group.getInstanceOf("id");
    		st.add("v", node.getIdentifier());
    		setResult(node, st);
    		return false;
    	}
    	
		public boolean visit(ForStatement node){
    		ST inits = group.getInstanceOf("args"), updates = group.getInstanceOf("args");
    		builds(inits, node.initializers());
    		builds(updates, node.updaters());
    		ST st = group.getInstanceOf("forStatement");
    		st.add("init", inits);
    		node.getExpression().accept(this);
    		st.add("exp1", this.getResult());
    		st.add("update", updates);
    		node.getBody().accept(this);
    		st.add("statement", this.getResult());
    		setResult(node, st);
    		return false;
    	}
    	public boolean visit(TypeDeclaration node){
    		if (node.getModifiers() > 0){
	    		ST st = group.getInstanceOf("classDeclaration");
	    		ST body = group.getInstanceOf("block");
	    		final Pair<MethodDeclaration, List<ASTNode>> p = getMainMethod(node.bodyDeclarations());
	    		builds(body, p.second);
	    		st.add("body", body);
	    		p.first.accept(this);
	    		st.add("mainmethod", getResult());
	    		setResult(node, st);
    		}else{
    			ST st = group.getInstanceOf("simpleClassDeclaration");
    			st.add("id1", node.getName().getIdentifier());
    			ST fields = group.getInstanceOf("block");
    			builds(fields, node.bodyDeclarations());
    			st.add("fields1", fields);
    			setResult(node, st);
    		}
    		return false;
    	}
    	public boolean visit(CompilationUnit node){
    		ST st = group.getInstanceOf("args");
    		if (!node.types().isEmpty()){
    			for (Object n : node.types()) {
					if (n instanceof TypeDeclaration){
						final TypeDeclaration class1 = (TypeDeclaration)n;
						if (class1.getModifiers() > 0){
							class1.accept(this);
							st.add("mainclass", getResult());
						}else{
							st.add("simpleclass", getResult());
						}
					}
				}
    		}
    		setResult(node, st);
    		return false;
    	}
    	public boolean visit(Block node){
    			ST body = group.getInstanceOf("block");
    			builds(body, node.statements());
	    		body.add("statement1", body);
	    		return false;
    	}
    	public boolean visit(MethodDeclaration node){
    		if(node.getModifiers() > 1 && node.getName().getIdentifier() == "main"){
    			ST st = group.getInstanceOf("mainMethodDeclaration");
    			if (!node.parameters().isEmpty()){
    				Object o = node.parameters().get(0);
    				if (o instanceof FieldDeclaration){
    					st.add("id1",((VariableDeclarationFragment) ((FieldDeclaration) o).fragments().get(0)).getName().getIdentifier());
    					node.getBody().accept(this);
    					st.add("methodBody1", getResult());
    				}
    			}
    		}else{
    			ST st = group.getInstanceOf("methodDeclaration");
    			node.getReturnType2().accept(this);
    			st.add("returnType", getResult());
    			st.add("id1", node.getName().getIdentifier());
    			ST params = group.getInstanceOf("args");
    			builds(params, node.parameters());
    			st.add("param1", params);
    			node.getBody().accept(this);
    			st.add("methodBody1", getResult());
    			String className = ((TypeDeclaration) node.getParent()).getName().getIdentifier();
    			st.add("className", className);
    			
    		}
    		return false;
    	}
    	public boolean visit(SingleVariableDeclaration node){
    		ST st = group.getInstanceOf("param");
    		node.getType().accept(this);
    		st.add("type1", getResult());
    		st.add("id1", node.getName().getIdentifier());
    		setResult(node, st);
    		return false;
    	}
    	public boolean visit(MethodInvocation node){
    		ST st = group.getInstanceOf("invExp2");
	    	st.add("id2", node.getName().getIdentifier());
	    	ST args = group.getInstanceOf("args");
	    	builds(args, node.arguments());
	    	st.add("args", args);
	    	if (node.getExpression() != null){
	    		ST st1 = group.getInstanceOf("invExp1");
	    		node.getExpression().accept(this);
	    		st1.add("id1", getResult());
	    		st1.add("classname", getType(node.getName()).toString());
	    		setResult(node, st1);
	    		return false;
	    	}
	    	setResult(node, st);
    		return false;
    	}
    	
    	
    	public boolean visit(ExpressionStatement node){
    		if (node.getExpression() != null){
    			final Expression e = node.getExpression();
    			if (e instanceof Assignment 
    					|| e instanceof MethodInvocation
    					|| e instanceof PostfixExpression){
    				ST st = group.getInstanceOf("assignStatement");
    				e.accept(this);
	    			st.add("assign1", getResult());
	    			setResult(node, st);
	    		}else{
	    			return true;
	    		}
    		}
    		return false;
    	}
    	public boolean visit(VariableDeclarationStatement node){
    		ST st = group.getInstanceOf("localDeclaration");
    		ST fragments = group.getInstanceOf("args");
    		builds(fragments, node.fragments());
    		st.add("id1", fragments);
    		node.getType().accept(this);
    		st.add("type",getResult());
    		setResult(node, st);
    		return false;
    	}
    
    
    }
    
    
    protected test(){}
    
}