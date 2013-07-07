package net.sf.anathema.character.main.library.trait.view.fx;

import javafx.scene.Node;
import javafx.scene.control.Label;
import net.miginfocom.layout.CC;
import org.tbee.javafx.scene.layout.MigPane;

public class FxConfigurableLayout {
  public static FxConfigurableLayout Right() {
    return new FxConfigurableLayout(new CC().alignX("right"), new CC().growX().pushX());
  }

  public static FxConfigurableLayout Left() {
    return new FxConfigurableLayout(new CC().alignX("left"), new CC());
  }

  private final CC labelAlignment;
  private final CC dotAlignment;
  public Label label;
  public Node display;

  public FxConfigurableLayout(CC dotAlignment, CC labelAlignment) {
    this.labelAlignment = labelAlignment;
    this.dotAlignment = dotAlignment;
  }

  public FxConfigurableLayout addLabel(Label jLabel) {
    this.label = jLabel;
    return this;
  }

  public FxConfigurableLayout addDisplay(Node component) {
    this.display = component;
    return this;
  }

  public void addTo(MigPane pane) {
    pane.add(label, labelAlignment);
    pane.add(display, dotAlignment);
  }
}