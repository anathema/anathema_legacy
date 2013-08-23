package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import net.sf.anathema.framework.view.util.OptionalView;
import org.tbee.javafx.scene.layout.MigPane;

public class FxOptionalOverview implements OptionalView {
  private final MigPane pane;
  private Node overview;
  private boolean isVisible = true;

  public FxOptionalOverview(MigPane pane) {
    this.pane = pane;
  }

  @Override
  public void toggle() {
    if (isVisible) {
      pane.getChildren().clear();
      isVisible = false;
    } else {
      pane.add(overview);
      isVisible = true;
    }
  }

  public void setOverview(Node overview) {
    this.overview = overview;
    pane.getChildren().clear();
    pane.add(overview);
  }
}
