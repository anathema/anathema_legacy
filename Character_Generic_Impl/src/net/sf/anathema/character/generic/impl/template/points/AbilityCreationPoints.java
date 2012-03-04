package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
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
    return "[favored:" //$NON-NLS-1$
            + getFavorableTraitCount() + ",favoredDots:" //$NON-NLS-1$
            + getFavoredDotCount() + ",defaultDots:" //$NON-NLS-1$
            + getDefaultDotCount() + "]"; //$NON-NLS-1$
  }

  @Override
  public AbilityCreationPoints clone() {
    return (AbilityCreationPoints) super.clone();
  }
}