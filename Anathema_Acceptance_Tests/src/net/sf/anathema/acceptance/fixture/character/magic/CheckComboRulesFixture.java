package net.sf.anathema.acceptance.fixture.character.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;

public class CheckComboRulesFixture extends AbstractCheckCharmFisture {

  public boolean isAllAbilities() {
    return getComboRules().combosAllAbilities();
  }

  private IComboRestrictions getComboRules() {
    return getCharm().getComboRules();
  }

  public boolean isComboable() {
    ICharm charm = getCharm();
    boolean defaultAllowed = getCharacterStatistics().getCombos().isComboLegal(charm);
    return getComboRules().isComboAllowed(defaultAllowed);
  }
}