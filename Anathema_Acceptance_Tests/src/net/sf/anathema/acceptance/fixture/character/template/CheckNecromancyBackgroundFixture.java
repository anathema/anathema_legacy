package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.dummy.character.magic.DummySpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;

public class CheckNecromancyBackgroundFixture extends AbstractCharacterColumnFixture {

  public String circle;

  private IAdditionalMagicLearnPool getBackground() {
    return getTemplate().getAdditionalRules().getAdditionalMagicLearnPools()[0];
  }

  public boolean isCircleAllowed() {
    DummySpell dummySpell = new DummySpell("Dummy"); //$NON-NLS-1$
    dummySpell.setCircleType(CircleType.valueOf(circle));
    return getBackground().isAllowedFor(getCharacterStatistics().getTraitConfiguration(), dummySpell);
  }
}
