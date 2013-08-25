package net.sf.anathema.framework.preferences;

import javafx.scene.Node;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class FxPreferencesView implements PreferencesView {
  private final Node node = new MigPane(LayoutUtils.fillWithoutInsets());

  public Node getNode() {
    return node;
  }
}
