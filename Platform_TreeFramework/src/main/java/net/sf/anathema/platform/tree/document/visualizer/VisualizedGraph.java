package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.platform.tree.view.AgnosticCascadeBuilder;

public interface VisualizedGraph {

  Area getDimension();

  boolean isSingleNode();

  void translateBy(double x, double y);

  void addTo(AgnosticCascadeBuilder cascade);
}