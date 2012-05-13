package net.sf.anathema.framework.view.util;

import org.jdesktop.swingx.JXCollapsiblePane;

import javax.swing.*;
import java.awt.*;

public class OptionalViewBar {
  private JPanel panel = new JPanel(new BorderLayout());
  private JPanel buttonBar = new JPanel();
  private JXCollapsiblePane collapsiblePane = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT);

  public OptionalViewBar() {
    panel.add(buttonBar, BorderLayout.EAST);
    panel.add(collapsiblePane, BorderLayout.CENTER);
    collapsiblePane.setCollapsed(true);
  }

  public void setView(String title, JComponent component) {
    collapsiblePane.removeAll();
    collapsiblePane.add(component);
    buttonBar.removeAll();
    Action toggleAction = collapsiblePane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
    JButton toggleButton = new JButton(toggleAction);
    toggleButton.setText(title);
    toggleButton.setUI(new TopDownButtonUI());
    buttonBar.add(toggleButton);
  }

  public JComponent getComponent() {
    return panel;
  }
}
