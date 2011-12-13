package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;

public class SetAbilitiesFavoredFixture extends AbstractCharacterRowEntryFixture {

  public String abilityType;

  @Override
  public void enterRow() throws Exception {
    AbilityType typeObject = AbilityType.valueOf(abilityType);
    IFavorableTrait favorableTrait = getCharacterStatistics().getTraitConfiguration().getFavorableTrait(typeObject);
    favorableTrait.getFavorization().setFavorableState(FavorableState.Favored);
  }
}