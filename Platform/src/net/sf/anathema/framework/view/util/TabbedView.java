package net.sf.anathema.framework.view.util;

import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class TabbedView implements IView {

  private final JTabbedPane tabbedPane = new JTabbedPane();

  @SuppressWarnings("MagicConstant")
  public TabbedView(TabDirection tabDirection) {
    tabbedPane.setTabPlacement(tabDirection.getPlacement());
  }

  public void addView(IView content, ContentProperties properties) {
    JComponent tabContent = content.getComponent();
    tabbedPane.addTab(properties.getName(), tabContent);
  }

  @Override
  public final JComponent getComponent() {
    return tabbedPane;
  }
}
