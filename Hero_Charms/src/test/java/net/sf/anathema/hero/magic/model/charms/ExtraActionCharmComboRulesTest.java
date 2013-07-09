package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.type.CharmType;
import net.sf.anathema.character.main.dummy.DummyCharmUtilities;
import net.sf.anathema.character.main.magic.model.combos.ExtraActionCharmComboRules;
import net.sf.anathema.character.main.magic.model.combos.IComboRules;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExtraActionCharmComboRulesTest extends AbstractComboRulesTestCase {

  private IComboRules rules = new ExtraActionCharmComboRules();

  @Test
  public void testCharmComboTwoExtraAction() throws Exception {
    Charm charm1 = DummyCharmUtilities.createCharm(CharmType.ExtraAction);
    Charm charm2 = DummyCharmUtilities.createCharm(CharmType.ExtraAction);
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  @Test
  public void testCharmComboExtraActionReflexive() throws Exception {
    Charm charm1 = DummyCharmUtilities.createCharm(CharmType.ExtraAction);
    Charm charm2 = DummyCharmUtilities.createCharm(CharmType.Reflexive);
    assertTrue(rules.isComboLegal(charm1, charm2));
  }

  @Test
  public void testCharmComboExtraActionCharmWithSupplementalOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.ExtraAction, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboExtraActionCharmWithSupplementalOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.ExtraAction, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboExtraActionCharmWithSupplementalOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.ExtraAction, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboAbilityExtraActionCharmCombosWithAttributeSupplementalAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.ExtraAction, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboAbilityExtraActionCharmCombosWithAttributeSupplementalForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.ExtraAction, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboAbilityExtraActionCharmCombosWithAttributeSimpleAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.ExtraAction, CharmType.Simple));
  }

  @Test
  public void testCharmComboAbilityExtraActionCharmCombosWithAttributeSimpleForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.ExtraAction, CharmType.Simple));
  }

  @Test
  public void testCharmComboExtraActionCharmWithSimpleOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.ExtraAction, CharmType.Simple));
  }

  @Test
  public void testCharmComboExtraActionCharmWithSimpleOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.ExtraAction, CharmType.Simple));
  }

  @Test
  public void testCharmComboExtraActionCharmWithSimpleOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.ExtraAction, CharmType.Simple));
  }

  @Test
  public void testCharmComboRestrictionAllAbilitiesWithSimpleCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.ExtraAction, CharmType.Simple));
  }

  @Test
  public void testCharmComboRestrictionAllAbilitiesWithSupplementalCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.ExtraAction, CharmType.Supplemental));
  }
}