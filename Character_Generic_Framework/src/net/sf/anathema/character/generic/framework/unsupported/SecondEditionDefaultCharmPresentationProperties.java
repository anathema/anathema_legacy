package net.sf.anathema.character.generic.framework.unsupported;

import java.awt.Dimension;

import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;

public class SecondEditionDefaultCharmPresentationProperties implements ICharmPresentationProperties {
  private final static String POLYGON = "5.5,11.3542 35.3236,11.3542 30.24724,3.5 155.2527,3.5 150.17636,11.3542 180.0,11.3542 180.0,82.64578 150.17636,82.64578 155.2527,90.5 30.24724,90.5 35.3236,82.64578 5.5,82.64578"; //$NON-NLS-1$

  @Override
  public Dimension getCharmDimension() {
    return new Dimension(180, 90);
  }

  @Override
  public String getCharmFramePolygonString() {
    return POLYGON;
  }

  @Override
  public Dimension getGapDimension() {
    return new Dimension(25, 50);
  }

  @Override
  public boolean isolateSingles() {
    return true;
  }
}