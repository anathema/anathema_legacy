package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.main.model.traits.TraitMap;

public interface IAdditionalTraitRules {

  boolean isAllowedTraitValue(GenericTrait trait, TraitMap map);
  
  boolean isWillpowerVirtueBased();
}