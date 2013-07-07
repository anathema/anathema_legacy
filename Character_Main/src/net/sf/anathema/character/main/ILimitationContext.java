package net.sf.anathema.character.main;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.template.ITraitLimitation;

public interface ILimitationContext {

  ITraitLimitation getEssenceLimitation();

  int getAge();

  CasteType getCasteType();

  IGenericTraitCollection getTraitCollection();
}
