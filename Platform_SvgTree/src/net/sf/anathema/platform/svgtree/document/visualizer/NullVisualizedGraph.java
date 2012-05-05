package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;

public class NullVisualizedGraph implements IVisualizedGraph {
  @Override
  public Dimension getDimension() {
    return new Dimension(0, 0);
  }

  @Override
  public boolean isSingleNode() {
    return false;
  }

  @Override
  public void translateBy(double x, double y) {
    //nothing to do
  }

  @Override
  public void addTo(Object cascade) {
    //nothing to do
  }
}
