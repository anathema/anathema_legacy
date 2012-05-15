package net.sf.anathema.lib.gui.dialog.core;

import javax.swing.JRootPane;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.WindowListener;

public interface ISwingFrameOrDialog {

  public void setTitle(String title);

  public JRootPane getRootPane();

  public void pack();

  public void setModal(boolean modal);

  public Container getContentPane();

  public void setDefaultCloseOperation(int closeOperation);

  public void addWindowListener(WindowListener windowListener);

  public void removeWindowListener(WindowListener windowListener);

  public void dispose();

  public Window getWindow();

  public void setResizable(boolean resizable);

  public void show();

  public void setVisible(boolean visible);

  public boolean isVisible();
}