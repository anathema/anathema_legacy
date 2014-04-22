package net.sf.anathema.hero.combos;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.combos.ComboRestrictions;
import net.sf.anathema.character.main.magic.charm.combos.IComboRestrictions;
import net.sf.anathema.character.main.magic.charm.duration.SimpleDuration;
import net.sf.anathema.character.main.magic.charm.type.CharmType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.traits.types.ValuedTraitType;
import net.sf.anathema.hero.combos.model.ComboImpl;
import net.sf.anathema.hero.combos.model.rules.AbstractComboArbitrator;
import net.sf.anathema.hero.dummy.DummyCharmUtilities;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ComboTest {

  private ComboImpl combo = new ComboImpl();
  private AbstractComboArbitrator comboRules = new AbstractComboArbitrator() {

    @Override
    protected boolean isCharmLegalByRules(Charm charm) {
      return charm.getDuration() == SimpleDuration.INSTANT_DURATION;
    }
  };

  protected final void addCharm(Charm charm) {
    if (comboRules.canBeAddedToCombo(combo, charm)) {
      combo.addCharm(charm, false);
    } else {
      throw new IllegalArgumentException("The charm " + charm.getId() + " is illegal in this combo.");
    }
  }

  protected static Charm createCharm(CharmType charmType, IComboRestrictions restrictions) {
    return DummyCharmUtilities.createCharm(charmType, restrictions);
  }

  protected static Charm createCharm(IComboRestrictions restrictions) {
    return createCharm(CharmType.Reflexive, restrictions);
  }

  @Test
  public void testCreation() throws Exception {
    assertEquals(0, combo.getCharms().length);
    assertTrue(combo.getName().getText().isEmpty());
    assertTrue(combo.getDescription().getText().isEmpty());
  }

  @Test
  public void testAddedCharmIsIllegal() throws Exception {
    Charm charm = DummyCharmUtilities.createCharm(CharmType.Reflexive);
    addCharm(charm);
    assertFalse(comboRules.canBeAddedToCombo(combo, charm));
  }

  @Test
  public void testOnlyInstantDurationCombos() throws Exception {
    final Charm dummy1 = DummyCharmUtilities.createCharm(CharmType.Reflexive);
    assertTrue(comboRules.canBeAddedToCombo(combo, dummy1));
    final Charm dummy2 = DummyCharmUtilities.createCharm("Other", new ComboRestrictions());
    assertFalse(comboRules.canBeAddedToCombo(combo, dummy2));
  }

  @Test
  public void testOnlyOneExtraActionCharm() {
    Charm extraActionCharm = DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3));
    assertTrue(comboRules.canBeAddedToCombo(combo, extraActionCharm));
    addCharm(extraActionCharm);
    assertFalse(
            comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3))));
  }

  @Test
  public void testOnlyOneSimpleCharm() {
    Charm simpleCharm = DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3));
    assertTrue(comboRules.canBeAddedToCombo(combo, simpleCharm));
    addCharm(simpleCharm);
    assertFalse(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3))));
  }

  @Test
  public void testSimpleCharmOfSamePrimaryPrerequisiteAsExtraAction() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3))));
    assertFalse(
            comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAttributeSimpleCharmsCombosWithAbilityExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(
            comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AttributeType.Appearance, 3))));
  }

  @Test
  public void testAbilitySimpleCharmCombosWithAttributeExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Appearance, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3))));
  }

  @Test
  public void testExtraActionCharmOfSamePrimaryPrerequisiteAsSimple() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(
            comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3))));
    assertFalse(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAbilitySimpleCharm() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testExtraActionOfSamePrimaryPrerequisiteAsSupplemental() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(
            comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3))));
    assertFalse(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAbilitySupplemental() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Perception, 3))));
  }

  @Test
  public void testSupplementalOfSamePrimaryPrerequisiteAsExtraAction() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AbilityType.Archery, 3))));
    assertFalse(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAbilitySupplementalCombosWithAttributeExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AbilityType.Awareness, 3))));
  }

  @Test
  public void testAttributeSimpleCombosWithAttributeExtraAction() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(
            comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AttributeType.Strength, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAttributeSimple() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AttributeType.Strength, 3)));
    assertTrue(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAttributeSupplemental() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AttributeType.Strength, 3)));
    assertTrue(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testAttributeSupplementalCombosWithAttributeExtraAction() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AttributeType.Strength, 3))));
  }

  @Test
  public void testAttributeSimpleCombosWithAttributeSupplemental() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AttributeType.Strength, 3)));
    assertTrue(
            comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testAttributeSupplementalCombosWithAttributeSimple() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules
            .canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AttributeType.Strength, 3))));
  }

  @Test
  public void testExtraActionRestriction() throws Exception {
    ComboRestrictions comboRestrictions = new ComboRestrictions();
    comboRestrictions.addRestrictedCharmType(CharmType.ExtraAction);
    addCharm(createCharm(comboRestrictions));
    assertFalse(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction)));
  }

  @Test
  public void canCloneCombos() throws Exception {
    combo.setId(1);
    combo.clone();
  }
}