package net.sf.anathema.character.library.trait.rules;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.hero.Hero;

public class FavorableTraitRules extends TraitRules implements IFavorableTraitRules {

  public FavorableTraitRules(TraitType traitType, ITraitTemplate template, Hero hero) {
    super(traitType, template, hero);
  }

  @Override
  public boolean isRequiredFavored() {
    return getTemplate().isRequiredFavored();
  }
}