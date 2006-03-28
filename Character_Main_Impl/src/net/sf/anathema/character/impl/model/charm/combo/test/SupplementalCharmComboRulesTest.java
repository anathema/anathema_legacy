package net.sf.anathema.character.impl.model.charm.combo.test;

import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.impl.model.charm.combo.IComboRules;
import net.sf.anathema.character.impl.model.charm.combo.SupplementalCharmComboRules;

public class SupplementalCharmComboRulesTest extends AbstractComboRulesTestCase {

  private IComboRules rules;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.rules = new SupplementalCharmComboRules();
  }

  public void testCharmComboSupplementalCharmWithReflexiveOfDifferentAbility() throws Exception {
    assertTrue(comboDifferentAbilityCharms(CharmType.Supplemental, CharmType.Reflexive));
  }

  public void testCharmComboTwoSupplementalOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.Supplemental, CharmType.Supplemental));
  }

  public void testCharmComboTwoSupplementalOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.Supplemental, CharmType.Supplemental));
  }

  public void testCharmComboTwoSupplementalOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.Supplemental, CharmType.Supplemental));
  }

  public void testCharmComboRestrictionAllAbilitiesWithSimpleCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.Supplemental, CharmType.Simple));
  }

  public void testCharmComboRestrictionAllAbilitiesWithExtraActionCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.Supplemental, CharmType.ExtraAction));
  }

  public void testCharmComboRestrictionAllAbilitiesWithSupplementalCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.Supplemental, CharmType.Supplemental));
  }

  public void testCharmComboAbilityAttributeSupplementalAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.Supplemental, CharmType.Supplemental));
  }

  public void testCharmComboAbilityAttributeSupplementalForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.Supplemental, CharmType.Supplemental));
  }
}