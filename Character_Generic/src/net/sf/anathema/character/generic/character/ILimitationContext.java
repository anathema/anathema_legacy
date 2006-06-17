package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.template.ITraitLimitation;

public interface ILimitationContext extends IGenericTraitCollection {

  public ITraitLimitation getEssenceLimitation();

  public ICasteType< ? extends ICasteTypeVisitor> getCasteType();
}