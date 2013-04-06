package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.tree.util.Area;

import java.awt.Color;

public class GenericCharmPresentationProperties extends ReflectionCloneableObject<GenericCharmPresentationProperties> implements
        ITreePresentationProperties {

  private Color color = Color.WHITE;

  @Override
  public Area getNodeDimension() {
    return new Area(180, 90);
  }

  @Override
  public Area getGapDimension() {
    return new Area(25, 50);
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public final Area getVerticalLineDimension() {
    return new Area(getGapDimension().width, getNodeDimension().height);
  }

  public void setColor(Color color) {
    this.color = color;
  }
}