package net.sf.anathema.lib.gui.dialog.core;

public class StaticDialogResult implements DialogResult {

  public static DialogResult Canceled() {
    return new StaticDialogResult(true);
  }

  public static DialogResult Confirmed() {
    return new StaticDialogResult(false);
  }

  private final boolean canceled;

  private StaticDialogResult(boolean canceled) {
    this.canceled = canceled;
  }

  @Override
  public boolean isCanceled() {
    return canceled;
  }

}
