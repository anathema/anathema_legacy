package net.sf.anathema.fx.hero.traitview;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class SimpleTraitViewPanel implements TraitViewPanel {
  private final MigPane pane = new MigPane(LayoutUtils.withoutInsets().wrapAfter(3).fillX());

  @Override
  public void remove(Node node) {
    pane.getChildren().remove(node);
  }

  @Override
  public void add(Node node) {
    add(node, new CC());
  }

  @Override
  public void add(Node node, CC constraints) {
    pane.add(node, constraints);
  }

  public Node getNode() {
    return pane;
  }
}