package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.dummy.character.magic.DummySpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;

public class CheckSorceryBackgroundFixture extends AbstractCharacterColumnFixture {

  public String id;

  private IAdditionalMagicLearnPool getBackground() {
    return getTemplate().getAdditionalRules().getAdditionalMagicLearnPools()[0];
  }

  public boolean isAllowed() {
    DummySpell dummySpell = new DummySpell(id);
    dummySpell.setCircleType(CircleType.Terrestrial);
    return getBackground().isAllowedFor(getCharacterStatistics().getTraitConfiguration(), dummySpell);
  }
}
