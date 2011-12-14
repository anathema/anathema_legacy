package net.sf.anathema.test.character.main.impl.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.impl.model.charm.Combo;
import net.sf.anathema.character.impl.model.charm.combo.ComboArbitrator;
import net.sf.anathema.character.impl.model.charm.combo.FirstEditionComboArbitrator;
import net.sf.anathema.dummy.character.magic.DummyCharmUtilities;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComboTest {

  private Combo combo = new Combo();
  private ComboArbitrator comboRules = new FirstEditionComboArbitrator();

  protected final void addCharm(ICharm charm) {
    if (comboRules.canBeAddedToCombo(combo, charm)) {
      combo.addCharm(charm);
    } else {
      throw new IllegalArgumentException("The charm " + charm.getId() + " is illegal in this combo."); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  protected static ICharm createCharm(CharmType charmType, IComboRestrictions restrictions) {
    return DummyCharmUtilities.createCharm(charmType, restrictions);
  }

  protected static ICharm createCharm(String duration, IComboRestrictions restrictions) {
    return DummyCharmUtilities.createCharm(duration, restrictions);
  }

  protected static ICharm createCharm(IComboRestrictions restrictions) {
    return createCharm(CharmType.Reflexive, restrictions);
  }

  @Test
  public void testCreation() throws Exception {
    assertEquals(0, combo.getCharms().length);
    assertNull(combo.getName().getText());
    assertNull(combo.getDescription().getText());
  }

  @Test
  public void testAddedCharmIsIllegal() throws Exception {
    ICharm charm = DummyCharmUtilities.createCharm(CharmType.Reflexive);
    addCharm(charm);
    assertFalse(comboRules.canBeAddedToCombo(combo, charm));
  }

  @Test
  public void testOnlyInstantDurationCombos() throws Exception {
    final ICharm dummy1 = DummyCharmUtilities.createCharm(CharmType.Reflexive);
    assertTrue(comboRules.canBeAddedToCombo(combo, dummy1));
    final ICharm dummy2 = DummyCharmUtilities.createCharm("Other", new ComboRestrictions()); //$NON-NLS-1$
    assertFalse(comboRules.canBeAddedToCombo(combo, dummy2));
  }

  @Test
  public void testComboRestrictionComboAllowed() throws Exception {
    assertFalse(comboRules.canBeAddedToCombo(combo, createCharm("Instant", new ComboRestrictions( //$NON-NLS-1$
      false,
      Boolean.FALSE))));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm("Other", new ComboRestrictions( //$NON-NLS-1$
      false,
      Boolean.TRUE))));
  }

  @Test
  public void testOnlyOneExtraActionCharm() {
    ICharm extraActionCharm = DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(
      AbilityType.Archery,
      3));
    assertTrue(comboRules.canBeAddedToCombo(combo, extraActionCharm));
    addCharm(extraActionCharm);
    assertFalse(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.ExtraAction,
      new ValuedTraitType(AbilityType.Archery, 3))));
  }

  @Test
  public void testOnlyOneSimpleCharm() {
    ICharm simpleCharm = DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3));
    assertTrue(comboRules.canBeAddedToCombo(combo, simpleCharm));
    addCharm(simpleCharm);
    assertFalse(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Simple,
      new ValuedTraitType(AbilityType.Archery, 3))));
  }

  @Test
  public void testSimpleCharmOfSamePrimaryPrerequisiteAsExtraAction() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Simple,
      new ValuedTraitType(AbilityType.Archery, 3))));
    assertFalse(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Simple,
      new ValuedTraitType(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAttributeSimpleCharmsCombosWithAbilityExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Simple,
      new ValuedTraitType(AttributeType.Appearance, 3))));
  }

  @Test
  public void testAbilitySimpleCharmCombosWithAttributeExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Appearance, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Simple,
      new ValuedTraitType(AbilityType.Archery, 3))));
  }

  @Test
  public void testExtraActionCharmOfSamePrimaryPrerequisiteAsSimple() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.ExtraAction,
      new ValuedTraitType(AbilityType.Archery, 3))));
    assertFalse(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.ExtraAction,
      new ValuedTraitType(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAbilitySimpleCharm() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.ExtraAction,
      new ValuedTraitType(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testExtraActionOfSamePrimaryPrerequisiteAsSupplemental() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.ExtraAction,
      new ValuedTraitType(AbilityType.Archery, 3))));
    assertFalse(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.ExtraAction,
      new ValuedTraitType(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAbilitySupplemental() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.ExtraAction,
      new ValuedTraitType(AttributeType.Perception, 3))));
  }

  @Test
  public void testSupplementalOfSamePrimaryPrerequisiteAsExtraAction() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Supplemental,
      new ValuedTraitType(AbilityType.Archery, 3))));
    assertFalse(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Supplemental,
      new ValuedTraitType(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAbilitySupplementalCombosWithAttributeExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Supplemental,
      new ValuedTraitType(AbilityType.Awareness, 3))));
  }

  @Test
  public void testAttributeSimpleCombosWithAttributeExtraAction() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Simple,
      new ValuedTraitType(AttributeType.Strength, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAttributeSimple() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AttributeType.Strength, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.ExtraAction,
      new ValuedTraitType(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAttributeSupplemental() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AttributeType.Strength, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.ExtraAction,
      new ValuedTraitType(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testAttributeSupplementalCombosWithAttributeExtraAction() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Supplemental,
      new ValuedTraitType(AttributeType.Strength, 3))));
  }

  @Test
  public void testAttributeSimpleCombosWithAttributeSupplemental() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Supplemental, new ValuedTraitType(AttributeType.Strength, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Simple,
      new ValuedTraitType(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testAttributeSupplementalCombosWithAttributeSimple() throws Exception {
    addCharm(DummyCharmUtilities.createCharm(CharmType.Simple, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(
      CharmType.Supplemental,
      new ValuedTraitType(AttributeType.Strength, 3))));
  }

  @Test
  public void testExtraActionRestriction() throws Exception {
    ComboRestrictions comboRestrictions = new ComboRestrictions();
    comboRestrictions.addRestrictedCharmType(CharmType.ExtraAction);
    addCharm(createCharm(comboRestrictions));
    assertFalse(comboRules.canBeAddedToCombo(combo, DummyCharmUtilities.createCharm(CharmType.ExtraAction)));
  }
}