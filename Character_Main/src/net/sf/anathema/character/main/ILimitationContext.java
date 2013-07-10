package net.sf.anathema.character.main;

import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.hero.concept.CasteType;

public interface ILimitationContext {

  ITraitLimitation getEssenceLimitation();

  int getAge();

  CasteType getCasteType();

  IGenericTraitCollection getTraitCollection();
}
