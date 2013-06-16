package net.sf.anathema.character.library.trait.rules;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;

public class FavorableTraitRules extends TraitRules implements IFavorableTraitRules {

  public FavorableTraitRules(TraitType traitType, ITraitTemplate template, ILimitationContext limitationContext) {
    super(traitType, template, limitationContext);
  }

  @Override
  public boolean isRequiredFavored() {
    return getTemplate().isRequiredFavored();
  }
}