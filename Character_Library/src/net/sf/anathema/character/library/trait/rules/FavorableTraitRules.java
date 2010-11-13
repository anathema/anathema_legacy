package net.sf.anathema.character.library.trait.rules;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public class FavorableTraitRules extends TraitRules implements IFavorableTraitRules {

  public FavorableTraitRules(ITraitType traitType, ITraitTemplate template, ILimitationContext limitationContext) {
    super(traitType, template, limitationContext);
  }

  public boolean isRequiredFavored() {
    return getTemplate().isRequiredFavored();
  }
}