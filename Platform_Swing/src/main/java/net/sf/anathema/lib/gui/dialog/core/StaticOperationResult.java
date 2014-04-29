package net.sf.anathema.lib.gui.dialog.core;

public class StaticOperationResult implements OperationResult {

  public static OperationResult Canceled() {
    return new StaticOperationResult(true);
  }

  public static OperationResult Confirmed() {
    return new StaticOperationResult(false);
  }

  private final boolean canceled;

  private StaticOperationResult(boolean canceled) {
    this.canceled = canceled;
  }

  @Override
  public boolean isCanceled() {
    return canceled;
  }
}