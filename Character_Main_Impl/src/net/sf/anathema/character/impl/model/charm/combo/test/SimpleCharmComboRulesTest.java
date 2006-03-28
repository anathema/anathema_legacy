package net.sf.anathema.character.impl.model.charm.combo.test;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.impl.model.charm.combo.IComboRules;
import net.sf.anathema.character.impl.model.charm.combo.SimpleCharmComboRules;
import net.sf.anathema.character.impl.model.charm.test.DummyCharmUtilities;

public class SimpleCharmComboRulesTest extends AbstractComboRulesTestCase {

  private IComboRules rules;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.rules = new SimpleCharmComboRules();
  }

  public void testCharmComboTwoSimple() {
    ICharm charm1 = DummyCharmUtilities.createCharm(CharmType.Simple);
    ICharm charm2 = DummyCharmUtilities.createCharm(CharmType.Simple);
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  public void testCharmComboSimpleReflexive() throws Exception {
    ICharm charm1 = DummyCharmUtilities.createCharm(CharmType.Simple);
    ICharm charm2 = DummyCharmUtilities.createCharm(CharmType.Reflexive);
    assertTrue(rules.isComboLegal(charm1, charm2));
  }

  public void testCharmComboSimpleCharmWithSupplementalOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.Simple, CharmType.Supplemental));
  }

  public void testCharmComboSimpleCharmWithSupplementalOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.Simple, CharmType.Supplemental));
  }

  public void testCharmComboSimpleCharmWithSupplementalOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.Simple, CharmType.Supplemental));
  }

  public void testCharmComboAbilitySimpleCharmCombosWithAttributeSupplemental() throws Exception {
    assertTrue(comboAbilityAttributeCharms(CharmType.Simple, CharmType.Supplemental));
  }

  public void testCharmComboSimpleCharmWithExtraActionOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  public void testCharmComboSimpleCharmWithExtraActionOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  public void testCharmComboSimpleCharmWithExtraActionOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  public void testCharmComboAbilitySimpleCharmCombosWithAttributeExtraAction() throws Exception {
    assertTrue(comboAbilityAttributeCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  public void testCharmComboRestrictionAllAbilitiesWithExtraActionCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.Simple, CharmType.ExtraAction));
  }

  public void testCharmComboRestrictionAllAbilitiesWithSupplementalCharm() throws Exception {
    assertTrue(comboAllAbilitiesCharmWithAbility(CharmType.Simple, CharmType.Supplemental));
  }
}