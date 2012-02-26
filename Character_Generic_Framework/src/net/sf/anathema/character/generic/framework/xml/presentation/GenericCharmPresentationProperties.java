package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

import java.awt.Color;
import java.awt.Dimension;

public class GenericCharmPresentationProperties extends ReflectionCloneableObject<GenericCharmPresentationProperties> implements
        ITreePresentationProperties {

  private String polygonString;
  private Dimension charmDimension;
  private Color color;

  @Override
  public String getNodeFramePolygonString() {
    return polygonString;
  }

  @Override
  public Dimension getNodeDimension() {
    return charmDimension;
  }

  @Override
  public Dimension getGapDimension() {
    return new Dimension(25, 50);
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public final Dimension getVerticalLineDimension() {
    return new Dimension(getGapDimension().width, getNodeDimension().height);
  }

  public void setPolygonString(String polygonString) {
    this.polygonString = polygonString;
  }

  public void setCharmDimension(Dimension dimension) {
    this.charmDimension = dimension;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}