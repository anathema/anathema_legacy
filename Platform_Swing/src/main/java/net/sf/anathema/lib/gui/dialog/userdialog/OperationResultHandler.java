package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.OperationResult;

public interface OperationResultHandler {

  OperationResultHandler NULL_HANDLER = result -> {};

  void handleOperationResult(OperationResult result);
}