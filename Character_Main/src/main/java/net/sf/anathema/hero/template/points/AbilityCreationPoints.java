package net.sf.anathema.hero.template.points;

import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class AbilityCreationPoints extends ReflectionCloneableObject<IAbilityCreationPoints> implements IAbilityCreationPoints {
  private final int defaultDotCount;
  private final int favoredCount;
  private final int favoredDotCount;

  public AbilityCreationPoints(int favoredAbilityCount, int favoredDotCount, int defaultDotCount) {
    this.favoredCount = favoredAbilityCount;
    this.favoredDotCount = favoredDotCount;
    this.defaultDotCount = defaultDotCount;
  }

  @Override
  public int getFavoredDotCount() {
    return favoredDotCount;
  }

  @Override
  public int getFavorableTraitCount() {
    return favoredCount;
  }

  @Override
  public int getDefaultDotCount() {
    return defaultDotCount;
  }

  @Override
  public int getExtraFavoredDotCount() {
    return 0;
  }

  @Override
  public int getExtraGenericDotCount() {
    return 0;
  }

  @Override
  public String toString() {
    return "[favored:" + getFavorableTraitCount() + ",favoredDots:" + getFavoredDotCount() + ",defaultDots:" + getDefaultDotCount() + "]";
  }

  @Override
  public AbilityCreationPoints clone() {
    return (AbilityCreationPoints) super.clone();
  }
}