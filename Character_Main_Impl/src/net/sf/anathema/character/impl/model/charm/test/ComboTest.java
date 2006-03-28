package net.sf.anathema.character.impl.model.charm.test;

import net.sf.anathema.character.generic.impl.magic.test.DummyMartialArtsCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.impl.model.charm.Combo;
import net.sf.anathema.character.impl.model.charm.combo.ComboArbitrator;
import net.sf.anathema.lib.testing.BasicTestCase;

public class ComboTest extends BasicTestCase {

  private Combo combo;
  private ComboArbitrator comboRules;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.comboRules = new ComboArbitrator();
    this.combo = new Combo();
  }

  protected final void addCharm(ICharm charm) {
    if (comboRules.canBeAddedToCombo(combo, charm)) {
      combo.addCharm(charm);
    }
    else {
      throw new IllegalArgumentException("The charm " + charm.getId() + " is illegal in this combo."); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  protected final static ICharm createCharm(CharmType charmType) {
    return new DummyMartialArtsCharm(DurationType.Instant, charmType);
  }

  protected final static ICharm createCharm(CharmType charmType, IGenericTrait prerequisite) {
    return new DummyMartialArtsCharm(
        DurationType.Instant,
        charmType,
        new ComboRestrictions(),
        new IGenericTrait[] { prerequisite });
  }

  protected final static ICharm createCharm(CharmType charmType, IComboRestrictions restrictions) {
    return new DummyMartialArtsCharm(DurationType.Instant, charmType, restrictions);
  }

  protected final static ICharm createCharm(DurationType durationType, IComboRestrictions restrictions) {
    return new DummyMartialArtsCharm(durationType, CharmType.Reflexive, restrictions);
  }

  protected final static ICharm createCharm(IComboRestrictions restrictions) {
    return createCharm(CharmType.Reflexive, restrictions);
  }

  public void testCreation() throws Exception {
    assertEquals(0, combo.getCharms().length);
    assertNull(combo.getName().getText());
    assertNull(combo.getDescription().getText());
  }

  public void testAddedCharmIsIllegal() throws Exception {
    ICharm charm = createCharm(CharmType.Reflexive);
    addCharm(charm);
    assertFalse(comboRules.canBeAddedToCombo(combo, charm));
  }

  public void testOnlyInstantDurationCombos() throws Exception {
    assertTrue(comboRules.canBeAddedToCombo(combo, new DummyMartialArtsCharm(DurationType.Instant, CharmType.Reflexive)));
    assertFalse(comboRules.canBeAddedToCombo(combo, new DummyMartialArtsCharm(DurationType.Other, CharmType.Reflexive)));
  }

  public void testComboRestrictionComboAllowed() throws Exception {
    assertFalse(comboRules.canBeAddedToCombo(combo, createCharm(DurationType.Instant, new ComboRestrictions(
        null,
        Boolean.FALSE))));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(DurationType.Other, new ComboRestrictions(
        null,
        Boolean.TRUE))));
  }

  public void testOnlyOneExtraActionCharm() {
    ICharm extraActionCharm = createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3));
    assertTrue(comboRules.canBeAddedToCombo(combo, extraActionCharm));
    addCharm(extraActionCharm);
    assertFalse(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.ExtraAction, new ValuedTraitType(
        AbilityType.Archery,
        3))));
  }

  public void testOnlyOneSimpleCharm() {
    ICharm simpleCharm = createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3));
    assertTrue(comboRules.canBeAddedToCombo(combo, simpleCharm));
    addCharm(simpleCharm);
    assertFalse(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Simple, new ValuedTraitType(
        AbilityType.Archery,
        3))));
  }

  public void testSimpleCharmOfSamePrimaryPrerequisiteAsExtraAction() throws Exception {
    addCharm(createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Simple, new ValuedTraitType(
        AbilityType.Archery,
        3))));
    assertFalse(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Simple, new ValuedTraitType(
        AbilityType.Athletics,
        3))));
  }

  public void testAttributeSimpleCharmsCombosWithAbilityExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Simple, new ValuedTraitType(
        AttributeType.Appearance,
        3))));
  }

  public void testAbilitySimpleCharmCombosWithAttributeExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Appearance, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Simple, new ValuedTraitType(
        AbilityType.Archery,
        3))));
  }

  public void testExtraActionCharmOfSamePrimaryPrerequisiteAsSimple() throws Exception {
    addCharm(createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.ExtraAction, new ValuedTraitType(
        AbilityType.Archery,
        3))));
    assertFalse(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.ExtraAction, new ValuedTraitType(
        AbilityType.Athletics,
        3))));
  }

  public void testAttributeExtraActionCombosWithAbilitySimpleCharm() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(createCharm(CharmType.Simple, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.ExtraAction, new ValuedTraitType(
        AttributeType.Dexterity,
        3))));
  }

  public void testExtraActionOfSamePrimaryPrerequisiteAsSupplemental() throws Exception {
    addCharm(createCharm(CharmType.Supplemental, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.ExtraAction, new ValuedTraitType(
        AbilityType.Archery,
        3))));
    assertFalse(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.ExtraAction, new ValuedTraitType(
        AbilityType.Athletics,
        3))));
  }

  public void testAttributeExtraActionCombosWithAbilitySupplemental() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(createCharm(CharmType.Supplemental, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.ExtraAction, new ValuedTraitType(
        AttributeType.Perception,
        3))));
  }

  public void testSupplementalOfSamePrimaryPrerequisiteAsExtraAction() throws Exception {
    addCharm(createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Supplemental, new ValuedTraitType(
        AbilityType.Archery,
        3))));
    assertFalse(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Supplemental, new ValuedTraitType(
        AbilityType.Athletics,
        3))));
  }

  public void testAbilitySupplementalCombosWithAttributeExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Supplemental, new ValuedTraitType(
        AbilityType.Awareness,
        3))));
  }

  public void testAttributeSimpleCombosWithAttributeExtraAction() throws Exception {
    addCharm(createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Simple, new ValuedTraitType(
        AttributeType.Strength,
        3))));
  }

  public void testAttributeExtraActionCombosWithAttributeSimple() throws Exception {
    addCharm(createCharm(CharmType.Simple, new ValuedTraitType(AttributeType.Strength, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.ExtraAction, new ValuedTraitType(
        AttributeType.Dexterity,
        3))));
  }

  public void testAttributeExtraActionCombosWithAttributeSupplemental() throws Exception {
    addCharm(createCharm(CharmType.Supplemental, new ValuedTraitType(AttributeType.Strength, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.ExtraAction, new ValuedTraitType(
        AttributeType.Dexterity,
        3))));
  }

  public void testAttributeSupplementalCombosWithAttributeExtraAction() throws Exception {
    addCharm(createCharm(CharmType.ExtraAction, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Supplemental, new ValuedTraitType(
        AttributeType.Strength,
        3))));
  }

  public void testAttributeSimpleCombosWithAttributeSupplemental() throws Exception {
    addCharm(createCharm(CharmType.Supplemental, new ValuedTraitType(AttributeType.Strength, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Simple, new ValuedTraitType(
        AttributeType.Dexterity,
        3))));
  }

  public void testAttributeSupplementalCombosWithAttributeSimple() throws Exception {
    addCharm(createCharm(CharmType.Simple, new ValuedTraitType(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.Supplemental, new ValuedTraitType(
        AttributeType.Strength,
        3))));
  }

  public void testExtraActionRestriction() throws Exception {
    ComboRestrictions comboRestrictions = new ComboRestrictions();
    comboRestrictions.addRestrictedCharmType(CharmType.ExtraAction);
    addCharm(createCharm(comboRestrictions));
    assertFalse(comboRules.canBeAddedToCombo(combo, createCharm(CharmType.ExtraAction)));
  }
}