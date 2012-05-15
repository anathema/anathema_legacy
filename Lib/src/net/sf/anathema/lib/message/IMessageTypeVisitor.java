package net.sf.anathema.lib.message;

public interface IMessageTypeVisitor {

  void visitError(MessageType visitedType);

  void visitNormal(MessageType visitedType);

  void visitWarning(MessageType visitedType);

  void visitInformation(MessageType visitedType);

  void visitQuestion(MessageType visitedType);

}
