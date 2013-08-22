package net.sf.anathema.framework.view.perspective;

import javafx.scene.Node;
import net.sf.anathema.framework.view.util.FxStack;
import net.sf.anathema.lib.util.Identifier;

public class CardContainer implements Container {


  private final Identifier title;
  private final FxStack cardPanel;

  public CardContainer(Identifier title, FxStack cardPanel) {
    this.title = title;
    this.cardPanel = cardPanel;
  }

  @Override
  public void setContent(Node node) {
    cardPanel.add(title, node);
  }
}
