package net.sf.anathema.framework.view.util;

import com.google.common.collect.Iterables;
import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.fx.FxThreading;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.LinkedHashMap;

public class FxStack {

  private final LinkedHashMap<Identifier, Node> namedNodes = new LinkedHashMap<>();
  private MigPane parent;

  public FxStack(MigPane parent) {
    this.parent = parent;
  }

  public void add(Identifier name, Node node) {
    namedNodes.put(name, node);
    addToContentToAllowFxToFullyInitialize(name);
  }

  private void addToContentToAllowFxToFullyInitialize(Identifier name) {
    show(name);
  }

  public void show(final Identifier name) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        parent.getChildren().clear();
        Node selectedNode = namedNodes.get(name);
        parent.add(selectedNode, new CC().push().grow());
      }
    });
  }

  public void showFirst() {
    Identifier first = Iterables.getFirst(namedNodes.keySet(), null);
    show(first);
  }
}