package net.sf.anathema.framework.view.messaging;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.message.IMessageTypeVisitor;
import net.sf.anathema.lib.message.MessageType;

public class MessageTypeImagePaths {

  private static final RelativePath errorPath = new RelativePath("icons/error.gif");
  private static final RelativePath warningPath = new RelativePath("icons/warning.gif");
  private static final RelativePath infoPath = new RelativePath("icons/info.gif");
  private static final RelativePath questionPath = new RelativePath("icons/question.gif");

  public RelativePath getIconPath(MessageType type) {
    final RelativePath[] icon = new RelativePath[1];
    type.accept(new IMessageTypeVisitor() {
      @Override
      public void visitError(MessageType visitedType) {
        icon[0] = errorPath;
      }

      @Override
      public void visitNormal(MessageType visitedType) {
        icon[0] = infoPath;
      }

      @Override
      public void visitWarning(MessageType visitedType) {
        icon[0] = warningPath;
      }

      @Override
      public void visitInformation(MessageType visitedType) {
        icon[0] = infoPath;
      }

      @Override
      public void visitQuestion(MessageType visitedType) {
        icon[0] = questionPath;
      }
    });
    return icon[0];
  }
}