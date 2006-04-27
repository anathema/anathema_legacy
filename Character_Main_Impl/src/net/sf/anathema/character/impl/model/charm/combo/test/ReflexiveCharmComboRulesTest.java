package net.sf.anathema.character.impl.model.charm.combo.test;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;

public class ReflexiveCharmComboRulesTest extends AbstractComboRulesTestCase {
  public void testCharmComboAttributeAbilityReflexiveForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.Reflexive, CharmType.Reflexive));
  }

  public void testCharmComboAttributeAbilityReflexiveAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.Reflexive, CharmType.Reflexive));
  }
}
