package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.GenericTrait;

public interface IAdditionalTraitRules {

  boolean isAllowedTraitValue(GenericTrait trait, IGenericTraitCollection collection);
  
  boolean isWillpowerVirtueBased();
}