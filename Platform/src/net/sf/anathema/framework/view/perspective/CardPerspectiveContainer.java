package net.sf.anathema.framework.view.perspective;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CardPerspectiveContainer implements PerspectiveContainer {

  private String title;
  private JPanel cardPanel;

  public CardPerspectiveContainer(String title, JPanel cardPanel) {
    this.title = title;
    this.cardPanel = cardPanel;
  }

  @Override
  public void setSwingContent(JComponent component) {
    cardPanel.add(component, title);
  }
}
