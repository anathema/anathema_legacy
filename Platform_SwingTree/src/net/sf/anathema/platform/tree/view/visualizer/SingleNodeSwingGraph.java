package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

import java.awt.Dimension;

public class SingleNodeSwingGraph extends SwingGraph {

  public SingleNodeSwingGraph(DefaultContainerCascade nodeContainer, Dimension dimension) {
    super(nodeContainer, dimension);
  }

  @Override
  public boolean isSingleNode() {
    return true;
  }
}