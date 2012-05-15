package net.sf.anathema.lib.gui.dialog.core;

import com.google.common.base.Preconditions;

import javax.swing.JDialog;
import javax.swing.JRootPane;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.WindowListener;

public class SwingDialog implements ISwingFrameOrDialog {

  private final JDialog dialog;

  public SwingDialog(JDialog dialog) {
    Preconditions.checkNotNull(dialog);
    this.dialog = dialog;
  }

  @Override
  public void setTitle(String title) {
    dialog.setTitle(title);
  }

  @Override
  public JRootPane getRootPane() {
    return dialog.getRootPane();
  }

  @Override
  public void pack() {
    dialog.pack();
  }

  @Override
  public void setModal(boolean modal) {
    dialog.setModal(modal);
  }

  @Override
  public Container getContentPane() {
    return dialog.getContentPane();
  }

  @Override
  public void setDefaultCloseOperation(int closeOperation) {
    dialog.setDefaultCloseOperation(closeOperation);
  }

  @Override
  public void addWindowListener(WindowListener windowListener) {
    dialog.addWindowListener(windowListener);
  }

  @Override
  public void removeWindowListener(WindowListener windowListener) {
    dialog.removeWindowListener(windowListener);
  }

  @Override
  public void dispose() {
    dialog.dispose();
    //Bugfix (gebhard) 27.09.2006: Workaround for Sun Bug 4726458
    //http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4726458
    //disposed JDialogs depending on other disposed JDialogs are not garbage collected
    //Workaround: remove its content to only have the dangling JDialog, but not its content any more
    dialog.getContentPane().removeAll();
  }

  @Override
  public Window getWindow() {
    return dialog;
  }

  @Override
  public void setResizable(boolean resizable) {
    dialog.setResizable(resizable);
  }

  @Override
  public void show() {
    dialog.show();
  }

  @Override
  public void setVisible(boolean visible) {
    dialog.setVisible(visible);
  }

  @Override
  public boolean isVisible() {
    return dialog.isVisible();
  }
}