package net.sf.anathema.framework.view.util.collapsible;

import org.jdesktop.swingx.JXCollapsiblePane;

import javax.swing.*;

public class CollapsiblePane {

  private JXCollapsiblePane collapsiblePane = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT);

  public CollapsiblePane() {
    collapsiblePane.setCollapsed(true);
    collapsiblePane.setAnimated(false);
  }

  public void setContent(JComponent component) {
    collapsiblePane.removeAll();
    collapsiblePane.add(component);
  }

  public Action getToggleAction() {
    return collapsiblePane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
  }

  public JComponent getComponent() {
    return collapsiblePane;
  }

  public boolean isExpanded() {
    return !collapsiblePane.isCollapsed();
  }

  public boolean isCollapsed() {
    return collapsiblePane.isCollapsed();
  }

  public void toggleCollapsedState() {
    collapsiblePane.setCollapsed(!collapsiblePane.isCollapsed());
  }

  public void collapse() {
    collapsiblePane.setCollapsed(true);
  }
}
