package net.sf.anathema.hero.traits.model.limitation;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class StaticTraitLimitation implements TraitLimitation {

  private final int staticLimit;

  public StaticTraitLimitation(int staticLimit) {
    this.staticLimit = staticLimit;
  }

  @Override
  public int getAbsoluteLimit(Hero hero) {
    return staticLimit;
  }

  @Override
  public int getCurrentMaximum(Hero hero, boolean modified) {
    return staticLimit;
  }

  public int getStaticLimit() {
    return staticLimit;
  }
}