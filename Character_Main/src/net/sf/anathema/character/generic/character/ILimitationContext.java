package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.ITraitLimitation;

public interface ILimitationContext {

  ITraitLimitation getEssenceLimitation();

  int getEssenceCap(boolean modified);

  int getAge();

  ICasteType getCasteType();

  IGenericTraitCollection getTraitCollection();
}
