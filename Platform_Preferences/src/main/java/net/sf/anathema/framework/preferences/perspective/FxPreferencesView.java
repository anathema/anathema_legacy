package net.sf.anathema.framework.preferences.perspective;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

public class FxPreferencesView implements PreferencesView {
  private final MigPane node = new MigPane(new LC().fill());

  public Node getNode() {
    return node;
  }

  public void show(String title, NodeHolder view) {
    node.getChildren().clear();
    Label label = new Label(title);
    label.setStyle("-fx-font-weight: bold");
    node.add(label, new CC().split(2));
    node.add(new Separator(), new CC().pushX().growX().wrap());
    node.add(view.getNode(), new CC().pushY().growY());
  }
}
