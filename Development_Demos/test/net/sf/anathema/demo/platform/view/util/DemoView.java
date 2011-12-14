package net.sf.anathema.demo.platform.view.util;

import net.sf.anathema.lib.gui.IView;

import javax.swing.*;

public final class DemoView implements IView {

  private JLabel content = new JLabel("Content"); //$NON-NLS-1$

  public JComponent getComponent() {
    return content;
  }
}