package net.sf.anathema.lib.gui.dialog.message;

import fr.xmichel.javafx.dialog.Dialog;
import javafx.stage.Window;
import net.sf.anathema.framework.fx.FxDialogExceptionHandler;
import net.sf.anathema.lib.message.IMessage;
import net.sf.anathema.lib.resources.NullStringProvider;

public class MessageDialogFactory {

  @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
  public static void showMessageDialog(Window owner, IMessage message) {
    Throwable throwable = message.getThrowable();
    if (throwable == null) {
      Dialog.showInfo("Info", message.getText(), owner);
    } else {
      //TODO (Swing->FX) Hand in proper resources
      new FxDialogExceptionHandler(new NullStringProvider(), owner).handle(message.getThrowable(), message.getText());
    }
  }
}