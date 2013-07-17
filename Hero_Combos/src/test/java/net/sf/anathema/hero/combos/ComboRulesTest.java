package net.sf.anathema.hero.combos;

import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.dummy.DummyCharmUtilities;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.duration.SimpleDuration;
import net.sf.anathema.character.main.magic.charm.type.CharmType;
import net.sf.anathema.character.main.magic.charm.combos.ComboRestrictions;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.ValuedTraitType;
import net.sf.anathema.hero.combos.model.rules.AbstractComboArbitrator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ComboRulesTest extends AbstractComboRulesTestCase {

  private AbstractComboArbitrator rules = new AbstractComboArbitrator() {
    @Override
    protected boolean isCharmLegalByRules(Charm charm) {
      return true;
    }
  };

  @Test
  public void testDurationComboLegal() throws Exception {
    assertTrue(rules.isCharmComboLegal(new DummyCharm("Instant", CharmType.Reflexive, new ComboRestrictions(), null)));
  }

  @Test
  public void illegalCharmIsRejected() throws Exception {
    rules = new AbstractComboArbitrator() {
      @Override
      protected boolean isCharmLegalByRules(Charm charm) {
        return false;
      }
    };
    assertFalse(rules.isCharmComboLegal(new DummyCharm("Other", CharmType.Reflexive, new ComboRestrictions(), null)));
  }

  @Test
  public void testRestrictionByCharmType() throws Exception {
    ComboRestrictions restrictions = new ComboRestrictions();
    restrictions.addRestrictedCharmType(CharmType.ExtraAction);
    Charm charm1 = DummyCharmUtilities.createCharm(CharmType.Supplemental, restrictions, new ValuedTraitType(AbilityType.Archery, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Archery, 3));
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  @Test
  public void testRestrictionById() throws Exception {
    ComboRestrictions restrictions = new ComboRestrictions();
    String forbiddenId = "DummyCharm";
    restrictions.addRestrictedCharmId(forbiddenId);
    Charm charm1 = DummyCharmUtilities.createCharm(CharmType.Supplemental, restrictions, new ValuedTraitType(AbilityType.Archery, 3));
    DummyCharm charm2 = new DummyCharm(forbiddenId);
    charm2.setDuration(SimpleDuration.INSTANT_DURATION);
    charm2.setCharmType(CharmType.ExtraAction);
    charm2.setPrerequisites(new ValuedTraitType[]{new ValuedTraitType(AbilityType.Archery, 3)});
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  @Test
  public void testRestrictionByPrerequisite() throws Exception {
    ComboRestrictions restrictions = new ComboRestrictions();
    restrictions.addRestrictedTraitType(AbilityType.Awareness);
    Charm charm1 = DummyCharmUtilities.createCharm(CharmType.Reflexive, restrictions, new ValuedTraitType(AbilityType.Archery, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(CharmType.ExtraAction, new ValuedTraitType(AbilityType.Awareness, 3));
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  @Test
  public void testCharmComboSelf() throws Exception {
    Charm charm1 = DummyCharmUtilities.createCharm(CharmType.Reflexive);
    assertFalse(rules.isComboLegal(charm1, charm1));
  }
}