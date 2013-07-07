package net.sf.anathema.character.main.traits.limitation;

import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class StaticTraitLimitation extends ReflectionCloneableObject<ITraitLimitation> implements ITraitLimitation {

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