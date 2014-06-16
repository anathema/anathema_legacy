package net.sf.anathema.hero.template.points;

import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class AttributeCreationPoints extends ReflectionCloneableObject<IAttributeCreationPoints> implements IAttributeCreationPoints {

  private final int primary;
  private final int secondary;
  private final int tertiary;
  private final int favoredPicks;
  private final int favoredDots;
  private final int genericDots;

  public AttributeCreationPoints(int primary, int secondary, int tertiary) {
    this(primary, secondary, tertiary, 0, 0, 0);
  }

  public AttributeCreationPoints(int primary, int secondary, int tertiary, int picks, int favoredDots,
                                 int genericDots) {
    this.primary = primary;
    this.secondary = secondary;
    this.tertiary = tertiary;
    this.favoredPicks = picks;
    this.favoredDots = favoredDots;
    this.genericDots = genericDots;
  }

  @Override
  public int getFavorableTraitCount() {
    return favoredPicks;
  }

  @Override
  public int getFavoredDotCount() {
    return 0;
  }

  @Override
  public int getExtraFavoredDotCount() {
    return favoredDots;
  }

  @Override
  public int getExtraGenericDotCount() {
    return genericDots;
  }

  @Override
  public int[] getCounts() {
    return new int[]{primary, secondary, tertiary};
  }

  @Override
  public int getCount(AttributeGroupPriority priority) {
    switch (priority) {
      case Primary:
        return primary;
      case Secondary:
        return secondary;
      case Tertiary:
        return tertiary;
      default:
        throw new IllegalArgumentException("Unknown Attribute Group Priority: " + priority);
    }
  }

  @Override
  public AttributeCreationPoints clone() {
    return (AttributeCreationPoints) super.clone();
  }

  @Override
  public int getDefaultDotCount() {
    return 0;
  }
}