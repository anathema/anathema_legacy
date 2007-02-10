package net.sf.anathema.character.generic.framework.xml.presentation;

import java.awt.Dimension;

import net.sf.anathema.character.generic.template.presentation.ITreePresentationProperties;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericCharmPresentationProperties extends ReflectionCloneableObject<GenericCharmPresentationProperties> implements
    ITreePresentationProperties {

  private String polygonString;
  private Dimension charmDimension;
  private Dimension gapDimension;
  private boolean isolateSingles = false;

  public String getNodeFramePolygonString() {
    return polygonString;
  }

  public Dimension getNodeDimension() {
    return charmDimension;
  }

  public Dimension getGapDimension() {
    return gapDimension;
  }

  public boolean isolateSingles() {
    return isolateSingles;
  }

  public void setPolygonString(String polygonString) {
    this.polygonString = polygonString;
  }

  public void setCharmDimension(Dimension dimension) {
    this.charmDimension = dimension;
  }

  public void setGapDimension(Dimension dimension) {
    this.gapDimension = dimension;
  }

  public void setIsolateSingles(boolean isolate) {
    this.isolateSingles = isolate;
  }
}