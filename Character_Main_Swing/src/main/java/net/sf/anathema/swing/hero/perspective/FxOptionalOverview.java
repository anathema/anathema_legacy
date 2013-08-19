package net.sf.anathema.swing.hero.perspective;

import net.sf.anathema.framework.view.util.OptionalView;
import org.tbee.javafx.scene.layout.MigPane;

public class FxOptionalOverview implements OptionalView {
  private MigPane pane;

  public FxOptionalOverview(MigPane pane) {
    this.pane = pane;
  }

  @Override
  public void toggle() {
    if (pane.isVisible()) {
      pane.setVisible(false);
    } else {
      pane.setVisible(true);
    }
  }
}
