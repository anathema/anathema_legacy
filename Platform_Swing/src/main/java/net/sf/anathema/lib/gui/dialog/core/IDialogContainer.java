package net.sf.anathema.lib.gui.dialog.core;

public interface IDialogContainer extends IDialogControl {

  /**
   * Executes this Dialog by showing a modal instance.
   */
  OperationResult show();

  /** Requests this dialog to finish ('ok' in dialogs, 'finish' in wizards.). If the dialog cannot
   * finish (because this action is disabled) this method will do nothing and return silently. */
  void requestFinish();
}