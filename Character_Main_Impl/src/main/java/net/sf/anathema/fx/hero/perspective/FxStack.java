package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import javafx.scene.Parent;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.HashMap;
import java.util.Map;

public class FxStack {

  private final Map<String, Node> namedNodes = new HashMap<>();
  private MigPane parent;

  public FxStack(MigPane parent) {
    this.parent = parent;
  }

  public void add(String name, Node node) {
    namedNodes.put(name, node);
  }

  public void show(String name) {
    parent.getChildren().clear();
    Node selectedNode = namedNodes.get(name);
    parent.add(selectedNode);
  }
}
