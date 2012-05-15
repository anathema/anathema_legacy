package net.sf.anathema.lib.gui.message;

import net.sf.anathema.lib.gui.icon.EmptyIcon;
import net.sf.anathema.lib.gui.image.LibGuiImageProvider;
import net.sf.anathema.lib.message.IMessageTypeVisitor;
import net.sf.anathema.lib.message.MessageType;

import javax.swing.Icon;

public class MessageTypeUi extends AbstractMessageTypeUi {

  public static final Icon normalIcon = EmptyIcon.DEFAULT_ICON;
  public static final Icon errorIcon = LibGuiImageProvider.getInstance().getImageIcon("message/small/error.gif"); //$NON-NLS-1$
  public static final Icon warningIcon = LibGuiImageProvider.getInstance().getImageIcon("message/small/warning.gif"); //$NON-NLS-1$
  public static final Icon infoIcon = LibGuiImageProvider.getInstance().getImageIcon("message/small/info.gif"); //$NON-NLS-1$
  public static final Icon questionIcon = LibGuiImageProvider.getInstance().getImageIcon("message/small/question.gif"); //$NON-NLS-1$

  private static MessageTypeUi instance = new MessageTypeUi();

  @Override
  public Icon getIcon(MessageType type) {
    final Icon[] icon = new Icon[1];
    type.accept(new IMessageTypeVisitor() {
      @Override
      public void visitError(MessageType visitedType) {
        icon[0] = errorIcon;
      }

      @Override
      public void visitNormal(MessageType visitedType) {
        icon[0] = normalIcon;
      }

      @Override
      public void visitWarning(MessageType visitedType) {
        icon[0] = warningIcon;
      }

      @Override
      public void visitInformation(MessageType visitedType) {
        icon[0] = infoIcon;
      }

      @Override
      public void visitQuestion(MessageType visitedType) {
        icon[0] = questionIcon;
      }
    });
    return icon[0];
  }

  private MessageTypeUi() {
    //no instance available
  }

  public static MessageTypeUi getInstance() {
    return instance;
  }
}