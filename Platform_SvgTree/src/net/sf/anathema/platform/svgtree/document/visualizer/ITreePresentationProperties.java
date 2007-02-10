package net.sf.anathema.platform.svgtree.document.visualizer;

import java.awt.Dimension;

public interface ITreePresentationProperties {
  
  public String getNodeFramePolygonString();

  public Dimension getNodeDimension();

  public Dimension getGapDimension();

  public boolean isolateSingles();
}