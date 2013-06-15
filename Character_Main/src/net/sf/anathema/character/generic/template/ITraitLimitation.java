package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.lib.lang.clone.ICloneable;

public interface ITraitLimitation extends ICloneable<ITraitLimitation> {

  int getAbsoluteLimit(ILimitationContext limitationContext);

  int getCurrentMaximum(ILimitationContext limitationContext, boolean modified);
}