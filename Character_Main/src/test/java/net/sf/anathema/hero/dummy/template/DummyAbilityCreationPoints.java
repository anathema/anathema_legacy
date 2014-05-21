package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.hero.template.points.IAbilityCreationPoints;

public class DummyAbilityCreationPoints implements IAbilityCreationPoints {

  @Override
  public IAbilityCreationPoints clone() {
    return new DummyAbilityCreationPoints();
  }

  @Override
  public int getFavoredDotCount() {
    return 0;
  }

  @Override
  public int getFavorableTraitCount() {
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
  public int getDefaultDotCount() {
    return 15;
  }
}
