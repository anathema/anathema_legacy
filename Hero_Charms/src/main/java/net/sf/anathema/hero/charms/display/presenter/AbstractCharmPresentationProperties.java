package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.RGBColor;

public abstract class AbstractCharmPresentationProperties implements CharmPresentationProperties {

  private RGBColor color;

  protected AbstractCharmPresentationProperties() {
    this(RGBColor.White);
  }

  protected AbstractCharmPresentationProperties(RGBColor color) {
    this.color = color;
  }

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
}