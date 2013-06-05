package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.DialogResult;

public interface DialogCloseHandler {

  DialogCloseHandler NULL_HANDLER = new DialogCloseHandler() {
    @Override
    public void handleDialogClose(DialogResult result) {
      //nothing to do
    }
  };

  void handleDialogClose(DialogResult result);
}