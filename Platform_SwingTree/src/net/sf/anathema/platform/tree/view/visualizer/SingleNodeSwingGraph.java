package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;
import net.sf.anathema.platform.tree.view.container.AggregatedCascade;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

import java.awt.Dimension;

public class SingleNodeSwingGraph implements IVisualizedGraph<AggregatedCascade> {
  private final Dimension dimension;
  private final DefaultContainerCascade container;

  public SingleNodeSwingGraph(Dimension dimension, DefaultContainerCascade nodeContainer) {
    this.dimension = dimension;
    this.container = nodeContainer;
  }

  @Override
  public Dimension getDimension() {
    return dimension;
  }

  @Override
  public boolean isSingleNode() {
    return true;
  }

  @Override
  public void translateBy(double x, double y) {
    container.moveBy(x, y);
  }

  @Override
  public void addTo(AggregatedCascade cascade) {
    cascade.add(container);
  }
}