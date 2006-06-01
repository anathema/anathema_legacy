package net.sf.anathema.character.generic.template.presentation;

import java.awt.Dimension;

public interface ICharmPresentationProperties {
  public String getCharmFramePolygonString();

  public Dimension getCharmDimension();

  public Dimension getGapDimension();

  public boolean isolateSingles();
}