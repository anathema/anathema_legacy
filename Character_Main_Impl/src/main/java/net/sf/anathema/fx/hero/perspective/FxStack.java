package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import javafx.scene.Parent;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.fx.FxThreading;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.HashMap;
import java.util.Map;

public class FxStack {

  private final Map<Identifier, Node> namedNodes = new HashMap<>();
  private MigPane parent;

  public FxStack(MigPane parent) {
    this.parent = parent;
  }

  public void add(Identifier name, Node node) {
    namedNodes.put(name, node);
  }

  public void show(final Identifier name) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        parent.getChildren().clear();
        Node selectedNode = namedNodes.get(name);
        parent.add(selectedNode);
      }
    });
  }
}