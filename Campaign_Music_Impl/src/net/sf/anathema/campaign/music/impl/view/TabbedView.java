package net.sf.anathema.campaign.music.impl.view;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class TabbedView implements IView {
  public static TabbedView CreateUp() {
    return new TabbedView(JTabbedPane.TOP);
  }

  public static TabbedView CreateDown() {
    return new TabbedView(JTabbedPane.BOTTOM);
  }

  private final JTabbedPane tabbedPane = new JTabbedPane();

  @SuppressWarnings("MagicConstant")
  private TabbedView(int tabDirection) {
    tabbedPane.setTabPlacement(tabDirection);
  }

  public void addView(JComponent tabContent, ContentProperties properties) {
    tabbedPane.addTab(properties.getName(), tabContent);
  }

  @Override
  public final JComponent getComponent() {
    return tabbedPane;
  }
}