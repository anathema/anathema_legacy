package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.tree.document.visualizer.IVisualizedGraph;
import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.platform.tree.view.SwingCascadeBuilder;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

public class SwingGraph implements IVisualizedGraph<SwingCascadeBuilder> {
  private final DefaultContainerCascade container;
  private final Area dimension;
  private final boolean containsSingleNode;

  public SwingGraph(DefaultContainerCascade container, Area dimension, boolean containsSingleNode) {
    this.container = container;
    this.dimension = dimension;
    this.containsSingleNode = containsSingleNode;
  }

  @Override
  public Area getDimension() {
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
