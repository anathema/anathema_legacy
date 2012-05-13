package net.sf.anathema.framework.view.util;

import net.infonode.tabbedpanel.TabDropDownListVisiblePolicy;
import net.infonode.tabbedpanel.TabbedPanelProperties;
import net.infonode.util.Direction;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.widgets.RevalidatingScrollPane;

import javax.swing.*;
import java.awt.*;

public class TabbedView implements IView {

  private static void initTabbedPaneProperties(TabbedPanelProperties paneProperties, TabDirection tabDirection) {
    paneProperties.removeSuperObject(paneProperties);
    Direction direction = tabDirection.getDirection();
    paneProperties.setTabAreaOrientation(direction);
    paneProperties.setTabReorderEnabled(false);
    paneProperties.setTabDeselectable(false);
    paneProperties.setEnsureSelectedTabVisible(true);
    paneProperties.setHighlightPressedTab(true);
    paneProperties.setTabDropDownListVisiblePolicy(TabDropDownListVisiblePolicy.NEVER);
    paneProperties.getTabAreaComponentsProperties().setStretchEnabled(true);
  }

  private final JTabbedPane tabbedPane = new JTabbedPane();

  public TabbedView(TabDirection tabDirection) {
    tabbedPane.setTabPlacement(tabDirection.getPlacement());
  }

  public void addView(IView content, ContentProperties properties) {
    JComponent tabContent = content.getComponent();
    if (properties.isNeedsScrollbar()) {
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
