package antlrv3.ast;

import org.antlr.runtime.Token;

public class PlusOperatorNode extends BinaryOperatorNode {

  public PlusOperatorNode(final Token op2) {
    super(op2);
  }

  @Override
  public int value() {
    return left().value() + right().value();
  }

}
