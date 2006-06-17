package net.sf.anathema.test.character.main.impl.combo;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;

public class SupplementalCharmComboRulesTest extends AbstractComboRulesTestCase {

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