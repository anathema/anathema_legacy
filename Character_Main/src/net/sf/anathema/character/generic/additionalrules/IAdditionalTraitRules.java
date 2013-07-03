package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.character.main.model.traits.TraitMap;

public interface IAdditionalTraitRules {

  boolean isAllowedTraitValue(ValuedTraitType trait, TraitMap map);
  
  boolean isWillpowerVirtueBased();
}