package net.sf.anathema.test.character.main.impl.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.impl.model.charm.combo.IComboRules;
import net.sf.anathema.character.impl.model.charm.combo.SimpleCharmComboRules;
import net.sf.anathema.dummy.character.magic.DummyCharmUtilities;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleCharmComboRulesTest extends AbstractComboRulesTestCase {

  private IComboRules rules = new SimpleCharmComboRules();

  @Test
  public void testCharmComboTwoSimple() {
    ICharm charm1 = DummyCharmUtilities.createCharm(CharmType.Simple);
    ICharm charm2 = DummyCharmUtilities.createCharm(CharmType.Simple);
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  @Test
  public void testCharmComboSimpleReflexive() throws Exception {
    ICharm charm1 = DummyCharmUtilities.createCharm(CharmType.Simple);
    ICharm charm2 = DummyCharmUtilities.createCharm(CharmType.Reflexive);
    assertTrue(rules.isComboLegal(charm1, charm2));
  }

  @Test
  public void testCharmComboSimpleCharmWithSupplementalOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.Simple, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboSimpleCharmWithSupplementalOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.Simple, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboSimpleCharmWithSupplementalOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.Simple, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboAbilitySimpleCharmCombosWithAttributeSupplementalAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.Simple, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboAbilitySimpleCharmCombosWithAttributeSupplementalForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.Simple, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboSimpleCharmWithExtraActionOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  @Test
  public void testCharmComboSimpleCharmWithExtraActionOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  @Test
  public void testCharmComboSimpleCharmWithExtraActionOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  @Test
  public void testCharmComboAbilitySimpleCharmCombosWithAttributeExtraActionAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  @Test
  public void testCharmComboAbilitySimpleCharmCombosWithAttributeExtraActionForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  @Test
  public void testCharmComboRestrictionAllAbilitiesWithExtraActionCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.Simple, CharmType.ExtraAction));
  }

  @Test
  public void testCharmComboRestrictionAllAbilitiesWithSupplementalCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.Simple, CharmType.Supplemental));
  }
}