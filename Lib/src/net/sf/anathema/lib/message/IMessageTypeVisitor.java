package net.sf.anathema.lib.message;

public interface IMessageTypeVisitor {

  public void visitError(MessageType visitedType);

  public void visitNormal(MessageType visitedType);

  public void visitWarning(MessageType visitedType);

  public void visitInformation(MessageType visitedType);

  public void visitQuestion(MessageType visitedType);

}
