package net.sf.anathema.lib.gui.message;

import net.sf.anathema.lib.gui.icon.EmptyIcon;
import net.sf.anathema.lib.gui.icon.SwingIcons;
import net.sf.anathema.lib.message.IMessageTypeVisitor;
import net.sf.anathema.lib.message.MessageType;

import javax.swing.Icon;

public class LargeIconMessageTypeUi extends AbstractMessageTypeUi {

  @Override
  public Icon getIcon(final MessageType type) {
    final Icon[] icon = new Icon[1];
    type.accept(new IMessageTypeVisitor() {
      @Override
      public void visitError(final MessageType visitedType) {
        icon[0] = SwingIcons.getOptionPaneErrorIcon();
      }

      @Override
      public void visitNormal(final MessageType visitedType) {
        icon[0] = new EmptyIcon();
      }

      @Override
      public void visitWarning(final MessageType visitedType) {
        icon[0] = SwingIcons.getOptionPaneWarningIcon();
      }

      @Override
      public void visitInformation(final MessageType visitedType) {
        icon[0] = SwingIcons.getOptionPaneInformationIcon();
      }

      @Override
      public void visitQuestion(final MessageType visitedType) {
        icon[0] = SwingIcons.getOptionPaneQuestionIcon();
      }
    });
    return icon[0];
  }
}