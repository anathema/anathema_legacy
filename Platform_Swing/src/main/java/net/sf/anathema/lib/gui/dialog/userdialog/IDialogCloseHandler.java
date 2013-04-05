package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.IDialogResult;

public interface IDialogCloseHandler {

  IDialogCloseHandler NULL_HANDLER = new IDialogCloseHandler() {
    @Override
    public void handleDialogClose(IDialogResult result) {
      //nothing to do
    }
  };

  void handleDialogClose(IDialogResult result);
}