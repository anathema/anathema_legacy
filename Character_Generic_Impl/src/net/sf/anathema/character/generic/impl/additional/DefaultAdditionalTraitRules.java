package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class DefaultAdditionalTraitRules implements IAdditionalTraitRules {

  public boolean isAllowedTraitValue(IGenericTrait trait, IGenericTraitCollection collection) {
    return true;
  }
}