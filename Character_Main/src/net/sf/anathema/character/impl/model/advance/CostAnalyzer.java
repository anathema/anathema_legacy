package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.model.Hero;

public class CostAnalyzer implements ICostAnalyzer {

  private final Hero hero;
  private final TraitMap traitCollection;

  public CostAnalyzer(Hero hero) {
    this.hero = hero;
    this.traitCollection = TraitModelFetcher.fetch(hero);
  }

  @Override
  public final boolean isOccultFavored() {
    return traitCollection.getTrait(AbilityType.Occult).isCasteOrFavored();
  }

  @Override
  public final boolean isMagicFavored(IMagic magic) {
    return magic.isFavored(hero);
  }

  @Override
  public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
    return MartialArtsUtilities.getLevel(charm);
  }
}