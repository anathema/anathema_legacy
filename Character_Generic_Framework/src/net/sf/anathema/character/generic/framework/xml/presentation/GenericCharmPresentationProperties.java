package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

import java.awt.Color;
import java.awt.Dimension;

public class GenericCharmPresentationProperties extends ReflectionCloneableObject<GenericCharmPresentationProperties> implements
        ITreePresentationProperties {

  private static final String polygonString = "5.5,11.3542 35.3236,11.3542 30.24724,3.5 155.2527,3.5 150.17636,11.3542 180.0,11.3542 180.0,82.64578 150.17636,82.64578 155.2527,90.5 30.24724,90.5 35.3236,82.64578 5.5,82.64578";
  private Color color = Color.WHITE;

  @Override
  public String getNodeFramePolygonString() {
    return polygonString;
  }

  @Override
  public Dimension getNodeDimension() {
    return new Dimension(180, 90);
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

  public void setColor(Color color) {
    this.color = color;
  }
}