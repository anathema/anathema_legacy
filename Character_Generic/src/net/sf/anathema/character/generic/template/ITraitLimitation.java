package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.lib.lang.clone.ICloneable;

public interface ITraitLimitation extends ICloneable<ITraitLimitation> {

  public int getAbsoluteLimit(ILimitationContext limitationContext);

  public int getCurrentMaximum(ILimitationContext limitationContext);
}