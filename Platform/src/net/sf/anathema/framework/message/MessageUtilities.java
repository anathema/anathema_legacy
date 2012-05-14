package net.sf.anathema.framework.message;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.logging.Logger;

import java.awt.Component;

public class MessageUtilities {

  private MessageUtilities() {
    throw new UnreachableCodeReachedException();
  }

  public static void indicateMessage(Class< ? > clazz, Component parentComponent, Message message) {
    Throwable throwable = message.getThrowable();
    if (throwable != null) {
      Logger.getLogger(clazz).error(throwable);
    }
    MessageDialogFactory.showMessageDialog(parentComponent, message);
  }
}