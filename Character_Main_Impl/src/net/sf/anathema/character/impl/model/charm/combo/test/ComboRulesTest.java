package net.sf.anathema.character.impl.model.charm.combo.test;

import net.sf.anathema.character.generic.impl.magic.test.DummyCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.impl.model.charm.combo.ComboArbitrator;
import net.sf.anathema.character.impl.model.charm.combo.FirstEditionComboArbitrator;
import net.sf.anathema.character.impl.model.charm.test.DummyCharmUtilities;

public class ComboRulesTest extends AbstractComboRulesTestCase {

  private ComboArbitrator rules;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.rules = new FirstEditionComboArbitrator();
  }

  public void testDurationComboLegal() throws Exception {
    assertTrue(rules.isCharmComboLegal(new DummyCharm("Instant", //$NON-NLS-1$
        CharmType.Reflexive,
        new ComboRestrictions(),
        null)));
    assertFalse(rules.isCharmComboLegal(new DummyCharm("Other", //$NON-NLS-1$
        CharmType.Reflexive,
        new ComboRestrictions(),
        null)));
  }

  public void testRestrictionComboLegal() throws Exception {
    assertFalse(rules.isCharmComboLegal(DummyCharmUtilities.createCharm("Instant", new ComboRestrictions(
        false,
        Boolean.FALSE))));
    assertTrue(rules.isCharmComboLegal(DummyCharmUtilities.createCharm("DurationType", new ComboRestrictions(
        false,
        Boolean.TRUE))));
  }

  public void testRestrictionByCharmType() throws Exception {
    ComboRestrictions restrictions = new ComboRestrictions();
    restrictions.addRestrictedCharmType(CharmType.ExtraAction);
    ICharm charm1 = DummyCharmUtilities.createCharm(CharmType.Supplemental, restrictions, new ValuedTraitType(
        AbilityType.Archery,
        3));
    ICharm charm2 = DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3));
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  public void testRestrictionById() throws Exception {
    ComboRestrictions restrictions = new ComboRestrictions();
    String forbiddenId = "DummyCharm";//$NON-NLS-1$
    restrictions.addRestrictedCharmId(forbiddenId);
    ICharm charm1 = DummyCharmUtilities.createCharm(CharmType.Supplemental, restrictions, new ValuedTraitType(
        AbilityType.Archery,
        3));
    DummyCharm charm2 = new DummyCharm(forbiddenId);
    charm2.setDuration(SimpleDuration.INSTANT_DURATION);
    charm2.setCharmType(CharmType.ExtraAction);
    charm2.setPrerequisites(new ValuedTraitType[] { new ValuedTraitType(AbilityType.Archery, 3) });
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  public void testRestrictionByPrerequisite() throws Exception {
    ComboRestrictions restrictions = new ComboRestrictions();
    restrictions.addRestrictedTraitType(AbilityType.Awareness);
    ICharm charm1 = DummyCharmUtilities.createCharm(CharmType.Reflexive, restrictions, new ValuedTraitType(
        AbilityType.Archery,
        3));
    ICharm charm2 = DummyCharmUtilities.createCharm(
        CharmType.ExtraAction,
        new ValuedTraitType(AbilityType.Awareness, 3));
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  public void testCharmComboSelf() throws Exception {
    ICharm charm1 = DummyCharmUtilities.createCharm(CharmType.Reflexive);
    assertFalse(rules.isComboLegal(charm1, charm1));
  }
}