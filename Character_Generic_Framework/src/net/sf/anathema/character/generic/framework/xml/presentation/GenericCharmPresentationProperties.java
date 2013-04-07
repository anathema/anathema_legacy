package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.tree.util.Area;
import net.sf.anathema.platform.tree.util.RGBColor;

public class GenericCharmPresentationProperties extends ReflectionCloneableObject<GenericCharmPresentationProperties> implements
        ITreePresentationProperties {

  private RGBColor color = RGBColor.White;

  @Override
  public Area getNodeDimension() {
    return new Area(180, 90);
  }

  @Override
  public Area getGapDimension() {
    return new Area(25, 50);
  }

  @Override
  public RGBColor getColor() {
    return color;
  }

  @Override
  public final Area getVerticalLineDimension() {
    return new Area(getGapDimension().width, getNodeDimension().height);
  }

  public void setColor(RGBColor color) {
    this.color = color;
  }

  @Override
  public GenericCharmPresentationProperties clone() {
    GenericCharmPresentationProperties properties = new GenericCharmPresentationProperties();
    properties.setColor(new RGBColor(color));
    return properties;
  }
}