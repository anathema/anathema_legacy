package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;

public class SetAttributeFavoredFixture extends AbstractCharacterRowEntryFixture {
  public String id;

  @Override
  public void enterRow() throws Exception {
    AttributeType typeObject = AttributeType.valueOf(id);
    IFavorableModifiableTrait favorableTrait = getCharacterStatistics().getTraitConfiguration().getFavorableTrait(typeObject);
    favorableTrait.getFavorization().setFavorableState(FavorableState.Favored);
  }
}