package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.template.ITraitLimitation;

public interface ILimitationContext {

  ITraitLimitation getEssenceLimitation();

  int getAge();

  CasteType getCasteType();

  IGenericTraitCollection getTraitCollection();
}
