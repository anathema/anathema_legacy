package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

import java.awt.Color;
import java.awt.Dimension;

public class GenericCharmPresentationProperties extends ReflectionCloneableObject<GenericCharmPresentationProperties> implements
        ITreePresentationProperties {

  private Color color = Color.WHITE;

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