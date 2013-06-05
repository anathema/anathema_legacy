package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.DialogResult;

public interface IDialogCloseHandler {

  IDialogCloseHandler NULL_HANDLER = new IDialogCloseHandler() {
    @Override
    public void handleDialogClose(DialogResult result) {
      //nothing to do
    }
  };

  void handleDialogClose(DialogResult result);
}