package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.experience.ICostAnalyzer;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class CostAnalyzer implements ICostAnalyzer {

  private final IBasicCharacterData basicCharacter;
  private final IGenericTraitCollection traitCollection;
  private final FavoringTraitType type;

  public CostAnalyzer(
      IBasicCharacterData basicCharacter,
      IGenericTraitCollection traitCollection,
      FavoringTraitType type) {
    this.basicCharacter = basicCharacter;
    this.traitCollection = traitCollection;
    this.type = type;
  }

  public final boolean isOccultFavored() {
    return traitCollection.getFavorableTrait(AbilityType.Occult).isCasteOrFavored();
  }

  public final boolean isMagicFavored(IMagic magic) {
    return magic.isFavored(basicCharacter, traitCollection, type);
  }

  public MartialArtsLevel getMartialArtsLevel(ICharm charm) {
    if (!(MartialArtsUtilities.isMartialArtsCharm(charm))) {
      return null;
    }
    return ((IMartialArtsCharm) charm).getLevel();
  }
}