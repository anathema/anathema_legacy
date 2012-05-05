package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;
import net.sf.anathema.platform.tree.view.container.AggregatedCascade;
import net.sf.anathema.platform.tree.view.container.SingleNodeContainer;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;

import java.awt.Dimension;

public class SwingSingleNodeGraph implements IVisualizedGraph<AggregatedCascade> {
  private final FilledPolygon element;
  private final Dimension dimension;

  public SwingSingleNodeGraph(FilledPolygon element, Dimension dimension) {
    this.element = element;
    this.dimension = dimension;
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
    element.moveBy((int) x, (int) y);
  }

  @Override
  public void addTo(AggregatedCascade cascade) {
    cascade.add(new SingleNodeContainer(element));
  }
}