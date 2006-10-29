package net.sf.anathema.demo.platform.view.util;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.sf.anathema.lib.gui.IView;

public final class DemoTabView implements IView {
  private JLabel content = new JLabel("Content"); //$NON-NLS-1$
  private final boolean needsScrollbar;

  public DemoTabView(boolean needsScrollbar) {
    this.needsScrollbar = needsScrollbar;
  }

  public boolean needsScrollbar() {
    return needsScrollbar;
  }

  public JComponent getComponent() {
    return content;
  }
}