package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.main.hero.Hero;

public class CostAnalyzer implements ICostAnalyzer {

  private final Hero hero;
  private final IGenericTraitCollection traitCollection;

  public CostAnalyzer(Hero hero, IGenericTraitCollection traitCollection) {
    this.hero = hero;
    this.traitCollection = traitCollection;
  }

  @Override
  public final boolean isOccultFavored() {
    return traitCollection.getTrait(AbilityType.Occult).isCasteOrFavored();
  }

  @Override
  public final boolean isMagicFavored(IMagic magic) {
    return magic.isFavored(hero, traitCollection);
  }

  @Override
  public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
    return MartialArtsUtilities.getLevel(charm);
  }
}