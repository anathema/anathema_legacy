package net.sf.anathema.lib.gui.dialog.core;

public class DialogResult implements IDialogResult {

  private final boolean canceled;

  public DialogResult(final boolean canceled) {
    this.canceled = canceled;
  }

  @Override
  public boolean isCanceled() {
    return canceled;
  }
}
