package net.sf.anathema.fx.hero.traitview;

import javafx.scene.Node;
import javafx.scene.control.Label;
import net.miginfocom.layout.CC;
import org.tbee.javafx.scene.layout.MigPane;

public class FxConfigurableLayout {
  public static FxConfigurableLayout Right() {
    return new FxConfigurableLayout(new CC().growX().pushX(), new CC().alignX("right"));
  }

  public static FxConfigurableLayout Single() {
    return new FxConfigurableLayout(new CC().growX().pushX().split(2), new CC().alignX("right"));
  }

  public static FxConfigurableLayout Left() {
    return new FxConfigurableLayout(new CC(), new CC().alignX("left"));
  }
  private final CC labelAlignment;
  private final CC dotAlignment;
  public Label label;

  public Node display;

  public FxConfigurableLayout(CC labelAlignment, CC dotAlignment) {
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