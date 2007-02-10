package net.sf.anathema.character.generic.template.presentation;

import java.awt.Dimension;

public interface ITreePresentationProperties {
  
  public String getNodeFramePolygonString();

  public Dimension getNodeDimension();

  public Dimension getGapDimension();

  public boolean isolateSingles();
}