package net.sf.anathema.framework.preferences.perspective;

import javafx.scene.Node;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

public class FxPreferencesView implements PreferencesView {
  private final MigPane node = new MigPane(LayoutUtils.fillWithoutInsets());

  public Node getNode() {
    return node;
  }

  public void show(NodeHolder view) {
    node.getChildren().clear();
    node.add(view.getNode());
  }
}
