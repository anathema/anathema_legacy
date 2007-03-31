package net.sf.anathema.acceptance.fixture.character.magic;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;

public class CheckSpellMagicFixture extends AbstractCharacterColumnFixture {

  public String id;

  public boolean isSpellFavored() {
    ICharacterModelContext characterContext = getCharacterStatistics().getCharacterContext();
    return getCharacterStatistics().getSpells().getSpellById(id).isFavored(
        characterContext.getBasicCharacterContext(),
        characterContext.getTraitCollection());
  }
}