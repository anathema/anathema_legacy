package net.sf.anathema.lib.gui.dialog.core;

import javax.swing.JRootPane;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.WindowListener;

public interface ISwingFrameOrDialog {

  void setTitle(String title);

  JRootPane getRootPane();

  void pack();

  void setModal(boolean modal);

  Container getContentPane();

  void addWindowListener(WindowListener windowListener);

  void removeWindowListener(WindowListener windowListener);

  void dispose();

  Window getWindow();

  void setResizable(boolean resizable);

  void show();

  void setVisible(boolean visible);

  boolean isVisible();
}