package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class FavorableTraitCreationPoints extends ReflectionCloneableObject<IFavorableTraitCreationPoints> implements
    IFavorableTraitCreationPoints {

  private final int defaultDotCount;
  private final int favoredCount;
  private final int favoredDotCount;

  public FavorableTraitCreationPoints(int favoredAbilityCount, int favoredDotCount, int defaultDotCount) {
    this.favoredCount = favoredAbilityCount;
    this.favoredDotCount = favoredDotCount;
    this.defaultDotCount = defaultDotCount;
  }

  public int getFavoredDotCount() {
    return favoredDotCount;
  }

  public int getFavorableTraitCount() {
    return favoredCount;
  }

  public int getDefaultDotCount() {
    return defaultDotCount;
  }

  @Override
  public String toString() {
    return "[favored:" //$NON-NLS-1$
        + getFavorableTraitCount()
        + ",favoredDots:" //$NON-NLS-1$
        + getFavoredDotCount()
        + ",defaultDots:" //$NON-NLS-1$
        + getDefaultDotCount()
        + "]"; //$NON-NLS-1$
  }

  @Override
  public FavorableTraitCreationPoints clone() {
    return (FavorableTraitCreationPoints) super.clone();
  }
}