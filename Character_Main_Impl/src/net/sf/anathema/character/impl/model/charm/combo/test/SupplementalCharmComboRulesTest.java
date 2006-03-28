package net.sf.anathema.character.impl.model.charm.combo.test;

import net.sf.anathema.character.generic.magic.charms.CharmType;

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
}