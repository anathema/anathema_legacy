package net.sf.anathema.lib.gui.dialog.core;

import net.sf.anathema.lib.gui.container.DisposableContainer;

import java.awt.Component;

public abstract class AbstractPage extends DisposableContainer implements IPage {

  /** @deprecated As of 10.11.2009 (gebhard), replaced by {@link #getHelpHandler()} */
  @Deprecated
  protected void performHelp() {
    throw new UnsupportedOperationException();
  }

  /** @deprecated As of 10.11.2009 (gebhard), replaced by {@link #getHelpHandler()} */
  @Deprecated
  protected boolean isHelpAvailable() {
    return false;
  }

  @Override
  public IDialogHelpHandler getHelpHandler() {
    return isHelpAvailable() ? new IDialogHelpHandler() {
      @Override
      public void execute(final Component parentComponent) {
        performHelp();
      }
    } : null;
  }

}