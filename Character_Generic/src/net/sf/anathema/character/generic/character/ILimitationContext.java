package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.ITraitLimitation;

public interface ILimitationContext {

  ITraitLimitation getEssenceLimitation();

  ICasteType getCasteType();

  IGenericTraitCollection getTraitCollection();

  int getAge();
  
  int getEssenceCap(boolean modified);
}
