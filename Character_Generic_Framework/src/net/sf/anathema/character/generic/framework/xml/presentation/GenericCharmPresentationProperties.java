package net.sf.anathema.character.generic.framework.xml.presentation;

import java.awt.Dimension;

import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericCharmPresentationProperties extends ReflectionCloneableObject implements
    ICharmPresentationProperties {

  private String polygonString;
  private Dimension charmDimension;
  private Dimension gapDimension;

  public String getCharmFramePolygonString() {
    return polygonString;
  }

  public Dimension getCharmDimension() {
    return charmDimension;
  }

  public Dimension getGapDimension() {
    return gapDimension;
  }

  public void setPolygonString(String polygonString) {
    this.polygonString = polygonString;
  }

  public void setCharmDimension(Dimension dimension) {
    charmDimension = dimension;
  }

  public void setGapDimension(Dimension dimension) {
    gapDimension = dimension;
  }
}