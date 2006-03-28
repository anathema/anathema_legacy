package net.sf.anathema.character.impl.model.charm.combo.test;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.impl.model.charm.combo.ExtraActionCharmComboRules;
import net.sf.anathema.character.impl.model.charm.combo.IComboRules;
import net.sf.anathema.character.impl.model.charm.test.DummyCharmUtilities;

public class ExtraActionCharmComboRulesTest extends AbstractComboRulesTestCase {

  private IComboRules rules;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.rules = new ExtraActionCharmComboRules();
  }

  public void testCharmComboTwoExtraAction() throws Exception {
    ICharm charm1 = DummyCharmUtilities.createCharm(CharmType.ExtraAction);
    ICharm charm2 = DummyCharmUtilities.createCharm(CharmType.ExtraAction);
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  public void testCharmComboExtraActionReflexive() throws Exception {
    ICharm charm1 = DummyCharmUtilities.createCharm(CharmType.ExtraAction);
    ICharm charm2 = DummyCharmUtilities.createCharm(CharmType.Reflexive);
    assertTrue(rules.isComboLegal(charm1, charm2));
  }

  public void testCharmComboExtraActionCharmWithSupplementalOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.ExtraAction, CharmType.Supplemental));
  }

  public void testCharmComboExtraActionCharmWithSupplementalOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.ExtraAction, CharmType.Supplemental));
  }

  public void testCharmComboExtraActionCharmWithSupplementalOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.ExtraAction, CharmType.Supplemental));
  }

  public void testCharmComboAbilityExtraActionCharmCombosWithAttributeSupplementalAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.ExtraAction, CharmType.Supplemental));
  }

  public void testCharmComboAbilityExtraActionCharmCombosWithAttributeSupplementalForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.ExtraAction, CharmType.Supplemental));
  }

  public void testCharmComboAbilityExtraActionCharmCombosWithAttributeSimpleAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.ExtraAction, CharmType.Simple));
  }

  public void testCharmComboAbilityExtraActionCharmCombosWithAttributeSimpleForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.ExtraAction, CharmType.Simple));
  }

  public void testCharmComboExtraActionCharmWithSimpleOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.ExtraAction, CharmType.Simple));
  }

  public void testCharmComboExtraActionCharmWithSimpleOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.ExtraAction, CharmType.Simple));
  }

  public void testCharmComboExtraActionCharmWithSimpleOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.ExtraAction, CharmType.Simple));
  }

  public void testCharmComboRestrictionAllAbilitiesWithSimpleCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.ExtraAction, CharmType.Simple));
  }

  public void testCharmComboRestrictionAllAbilitiesWithSupplementalCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.ExtraAction, CharmType.Supplemental));
  }
}