package net.sf.anathema.character.generic.impl.traits.limitation;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class StaticTraitLimitation extends ReflectionCloneableObject<ITraitLimitation> implements ITraitLimitation {

  private final int staticLimit;

  public StaticTraitLimitation(int staticLimit) {
    this.staticLimit = staticLimit;
  }

  public int getAbsoluteLimit(ILimitationContext limitationContext) {
    return staticLimit;
  }

  public int getCurrentMaximum(ILimitationContext limitationContext, boolean modified) {
    return staticLimit;
  }

  public int getStaticLimit() {
    return staticLimit;
  }
}