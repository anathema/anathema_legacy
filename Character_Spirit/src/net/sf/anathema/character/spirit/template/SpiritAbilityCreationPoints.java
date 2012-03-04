package net.sf.anathema.character.spirit.template;

import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class SpiritAbilityCreationPoints extends ReflectionCloneableObject<IAbilityCreationPoints> implements IAbilityCreationPoints {

  @Override
  public int getDefaultDotCount() {
    return 0;
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
  public int getFavorableTraitCount() {
    return 1;
  }

  @Override
  public int getFavoredDotCount() {
    return 0;
  }
}
