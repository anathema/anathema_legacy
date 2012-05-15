package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.IDialogResult;

public interface IDialogCloseHandler {

  public final static IDialogCloseHandler NULL_HANDLER = new IDialogCloseHandler() {
    @Override
    public void handleDialogClose(final IDialogResult result) {
      //nothing to do
    }
  };

  public void handleDialogClose(IDialogResult result);
}