package net.sf.anathema.character.main.xml.presentation;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public class GenericCharmPresentationProperties extends ReflectionCloneableObject<GenericCharmPresentationProperties> implements
        TreePresentationProperties {

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