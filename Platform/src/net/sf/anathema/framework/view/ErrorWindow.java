package net.sf.anathema.framework.view;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class ErrorWindow implements IWindow {

  private Exception cause;

  public ErrorWindow(Exception cause) {
    this.cause = cause;
  }

  @Override
  public void show() {
    JOptionPane.showMessageDialog(
      null,
      cause.getMessage() + "\n" + cause.getStackTrace()[0].toString(), "Initialization Error", ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
  }
}