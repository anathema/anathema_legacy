package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;
import net.sf.anathema.platform.tree.view.SwingCascadeBuilder;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

import java.awt.Dimension;

public class SwingGraph implements IVisualizedGraph<SwingCascadeBuilder> {
  private final DefaultContainerCascade container;
  private final Dimension dimension;
  private final boolean containsSingleNode;

  public SwingGraph(DefaultContainerCascade container, Dimension dimension, boolean containsSingleNode) {
    this.container = container;
    this.dimension = dimension;
    this.containsSingleNode = containsSingleNode;
  }

  @Override
  public Dimension getDimension() {
    return dimension;
  }

  @Override
  public boolean isSingleNode() {
    return containsSingleNode;
  }

  @Override
  public void translateBy(double x, double y) {
    container.moveBy(x, y);
  }

  @Override
  public void addTo(SwingCascadeBuilder builder) {
    builder.add(container);
  }
}
