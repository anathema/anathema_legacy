package net.sf.anathema.lib.gui.layout;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.Box;
import javax.swing.JPanel;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ButtonPanelBuilder {
  private final List<Component> components = new ArrayList<Component>();

  public void add(Component... newComponents) {
    Collections.addAll(components, newComponents);
  }

  public JPanel createPanel() {
    JPanel panel = new JPanel(new MigLayout(new LC().insets("4")));
    panel.add(Box.createGlue(), new CC().pushX().growX());
    for (Component component : components) {
      panel.add(component, new CC().minWidth("70").growX());
    }
    return panel;
  }
}