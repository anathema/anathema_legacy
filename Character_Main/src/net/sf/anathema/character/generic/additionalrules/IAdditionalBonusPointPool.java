package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.character.main.model.traits.TraitMap;

public interface IAdditionalBonusPointPool {

  int getAmount(TraitMap traitCollection);

  boolean isAllowedForTrait(TraitMap traitCollection, ValuedTraitType trait);

  boolean isAllowedForMagic(TraitMap traitCollection, IMagic magic);
}