package net.sf.anathema.demo.platform.view.util;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.sf.anathema.lib.gui.IView;

public final class DemoView implements IView {
  private JLabel content = new JLabel("Content"); //$NON-NLS-1$

  public JComponent getComponent() {
    return content;
  }
}