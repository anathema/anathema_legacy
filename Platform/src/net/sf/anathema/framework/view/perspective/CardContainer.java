package net.sf.anathema.framework.view.perspective;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CardContainer implements Container {

  private String title;
  private JPanel cardPanel;

  public CardContainer(String title, JPanel cardPanel) {
    this.title = title;
    this.cardPanel = cardPanel;
  }

  @Override
  public void setSwingContent(JComponent component) {
    cardPanel.add(component, title);
  }
}
