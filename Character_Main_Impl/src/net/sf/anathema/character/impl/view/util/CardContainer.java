package net.sf.anathema.character.impl.view.util;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sf.anathema.lib.gui.widgets.RevalidatingScrollPane;

public class CardContainer {

  private final CardLayout cardLayout = new CardLayout();
  private final JPanel cardPanel = new JPanel(cardLayout);

  public void showComponent(String name) {
    cardLayout.show(cardPanel, name);
  }

  public void addComponent(Component card, String name) {
    cardPanel.add(card, name);
  }

  public void addScrollableComponent(Component component, String name) {
    JPanel viewComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
    viewComponent.add(component);
    JScrollPane card = new RevalidatingScrollPane(viewComponent);
    addComponent(card, name);
  }

  public Component getComponent() {
    return cardPanel;
  }

}