package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.framework.ui.Area;

public interface IVisualizedGraph<G> {

  Area getDimension();

  boolean isSingleNode();

  void translateBy(double x, double y);

  void addTo(G cascade);
}