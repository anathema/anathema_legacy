package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.OperationResult;

public interface OperationResultHandler {

  OperationResultHandler NULL_HANDLER = new OperationResultHandler() {
    @Override
    public void handleOperationResult(OperationResult result) {
      //nothing to do
    }
  };

  void handleOperationResult(OperationResult result);
}