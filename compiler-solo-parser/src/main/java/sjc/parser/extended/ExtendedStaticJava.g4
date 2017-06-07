grammar ExtendedStaticJava;

compilationUnit
  : (sc1+=simpleClassDeclaration*)cd=classDefinition (sc2+=simpleClassDeclaration*) EOF
  ;
  
  
simpleClassDeclaration
 : 'class' ID '{'
 publicFieldDeclaration* 
 '}' 
 ;
 
 
//publicFieldDeclaration
//: 'public' type ID ';' 
//;


classDefinition
  : 'public' 'class' ID '{' 
    mainMethodDeclaration
    //( fieldDeclaration | methodDeclaration )* 
    (fieldOrMethodDeclaration)*
     '}'
  ;
  
fieldOrMethodDeclaration
   : fieldDeclaration
   | methodDeclaration
	;
   
mainMethodDeclaration
  : 'public' 'static' 'void'
    id1=ID                   { "main".equals($id1.text) }? 
    '(' id2=ID               { "String".equals($id2.text) }?
    '[' ']' id3=ID ')'
    '{' methodBody '}'
  ;
  

fieldDeclaration
  : 'static' type ID ';'
  ;

publicFieldDeclaration
  : 'public' type ID ';' 
  ;
  
methodDeclaration
  : 'static' returnType ID '(' params? ')' '{' methodBody '}'
  ;
  
  
returnType
  : 'void'                   #VoidType
  | type                     #NonVoidReturnType
  ;
  
  params
  : param ( ',' param )*
  ;
  
param
  : type ID
  ;

methodBody
  : localDeclaration* statement*
  ;
  
localDeclaration
  : type ID ';'
  ;
 
type
  : basicType
  | classType 
  | arrayType
  ;

arrayType
	:classType '[' ']'  #ClassTypeArray	 
	|basicType '[' ']' 	#BasicTypeArray	
	;

classType
	: ID 				#ClassTypeId	
	;
	
basicType
  : 'boolean'                #BooleanType
  | 'int'                    #IntType
  ;
  

  statement
  : ( assignStatement
    | ifStatement
    | whileStatement
    | invokeExpStatement
    | returnStatement
	| doWhileStatement
	| forStatement
	| incdec
    )
  ;
  
  
  
  
assignStatement 
 : lhs '=' exp ';'
 ;
assign 
 : lhs '=' exp
 ;
  
  lhs 
  : ID                    #AccessVar
  | exp '.'  ID           #AccessField
  | e1=exp '[' e2=exp ']' #AccessArray
  ;
  
ifStatement
  : 'if' '(' exp ')' '{' ts+=statement* '}'
    ( 'else' '{' fs+=statement* '}' )?
  ;
  
whileStatement
  : 'while' '(' exp ')' '{' statement* '}'
  ;
  
invokeExpStatement
  : invoke ';'
  ;
  
returnStatement
  : 'return' ( exp )? ';'
  ;
  
doWhileStatement
	: 'do' '{' statement* '}' 
	  'while' '(' exp ')' ';'
	;
  
forStatement     
 : 'for' '(' (forInit)? ';' (exp)? ';' (forUpdate)? ')'
	  '{' statement* '}'
	;
	
forInit
   : assign ( ',' assign )*
   ;
   
   
/*incDecStatement 
  : lhs op='++'
  | lhs op='--' ';'
  ;
  
incdec
  : lhs op='++'
  | lhs op='--'
  ;*/
  incdecStatement
  : lhs op=incdecop 
  ;  
  
incdec
  : lhs op=incdecop ';'
  ;

incdecop
  : '++' | '--'
  ;	  
  
forUpdate 
  : incdecStatement (',' incdecStatement)* 
  ;
 
exp
    :literalExp 					#LiteralEx
  	|'(' exp ')' 					#ParenExp
  	|invoke							#InvokeExp
  	|ID								#IdExp	
  	| op=( '-' | '+' ) exp          #UnaryExp
    | op=('!'|'~') exp               #UnaryExp
    |e1=exp '[' e2=exp ']' 			#ArrayAccess
  	|e1=exp '.' ID 					#FieldAccess
  	| e1=exp 
      op=( '*' | '/' | '%' )
      e2=exp                   #BinaryExp
   | e1=exp 
     op=( '+' | '-' )
     e2=exp                    #BinaryExp
   | e1=exp 
     op=( '<<'|'>>'|'>>>')
     e2=exp   				   #BinaryExp
   | e1=exp 
     op=( '<' | '>' | '<=' | '>=' )
     e2=exp                     #BinaryExp
   | e1=exp 
     op=( '==' | '!=' )
     e2=exp                     #BinaryExp
   | e1=exp op='&&' e2=exp      #BinaryExp
   | e1=exp op='||' e2=exp      #BinaryExp
  	
  	|newExp 						#NewEx
  	|e1=exp '?' e2=exp ':' e3=exp	#CondExp
  ;

  
 literalExp 
 : boolean_literal   #BooleanLiteral
 | INT               #IntLiteral
 |'null'             #NullLiteral
 ;
 
 boolean_literal 
  : 'true'          #TrueLiteral
  | 'false'         #FalseLiteral
  ;
  
newExp 
  : 'new' ID '(' ')'               #CreateClass
  | 'new' type '[' exp ']'         #CreateArrayExp
  | 'new' type '[' ']' arrayInit   #CreateArrayInit   
  ;
  
arrayInit 
  : '{' exp ( ',' exp )* '}'
  ;
  
//arrayAccessExp 
// : exp '[' exp ']'
// ;
 
//fieldAccessExp 
 //: exp '.' ID
// ;
  
//condExp
// : e1=exp '?' e2=exp ':' e3=exp
 //;
 
//binaryExp 
 //: e1=exp binaryOp e2=exp
 //;
 
binaryOp 
  : '+' 
  | '-' 
  | '*' 
  | '/' 
  | '%' 
  | '>' 
  | '>=' 
  | '==' 
  | '<'
  | '<=' 
  | '!=' 
  | '&&' 
  | '||' 
  | '<<' 
  | '>>' 
  | '>>>'
  ;
  
//unaryExp 
 // : unaryOp exp
  //;
 
unaryOp 
  : '+' 
  | '-' 
  | '!' 
  | '~'
  ;
//parenExp 
 //:'(' exp ')'
 //;

invoke
  : ( id1=ID '.' )? id2=ID '(' args? ')'
  ;
  
args
  : exp ( ',' exp )*
  ;

ID
  : ( 'a'..'z' | 'A'..'Z' | '_' | '$' ) 
    ( 'a'..'z' | 'A'..'Z' | '_' | '0'..'9' | '$' )*
  ;
  
INT
  : '0' | ('1'..'9') ('0'..'9')*
  ;

// Whitespace -- ignored
WS
  : [ \r\t\u000C\n]+ -> skip
  ;
  
// Any other character is an error character
ERROR
  : .
  ;