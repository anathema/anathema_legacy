package net.sf.anathema.framework.view.util;

import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.widgets.RevalidatingScrollPane;

import javax.swing.*;
import java.awt.*;

public class TabbedView implements IView {

  private final JTabbedPane tabbedPane = new JTabbedPane();

  public TabbedView(TabDirection tabDirection) {
    tabbedPane.setTabPlacement(tabDirection.getPlacement());
  }

  public void addView(IView content, ContentProperties properties) {
    JComponent tabContent = content.getComponent();
    if (properties.isScrollBarNeeded()) {
      JPanel viewComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
      viewComponent.add(tabContent);
      tabContent = new RevalidatingScrollPane(viewComponent);
    }
    tabbedPane.addTab(properties.getName(), tabContent);
  }

  @Override
  public final JComponent getComponent() {
    return tabbedPane;
  }
}
