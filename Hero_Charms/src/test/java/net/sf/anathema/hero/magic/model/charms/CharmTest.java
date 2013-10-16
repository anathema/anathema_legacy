package net.sf.anathema.hero.magic.model.charms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import net.sf.anathema.character.main.magic.basic.cost.CostList;
import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.charm.combos.ComboRestrictions;
import net.sf.anathema.character.main.magic.charm.duration.SimpleDuration;
import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.type.CharmType;
import net.sf.anathema.character.main.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.main.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.character.main.magic.parser.charms.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;

import org.junit.Test;

public class CharmTest {

  @Test
  public void testParentCharmsNotOverwritten() throws Exception {
    DummyCharm dummy = new DummyCharm("OtherDummy");
    CharmImpl charm = createCharm(dummy);
    charm.extractParentCharms(new HashMap<String, CharmImpl>());
    assertEquals(1, charm.getParentCharms().size());
    assertEquals(dummy, charm.getParentCharms().toArray(new Charm[1])[0]);
  }

  private CharmImpl createCharm(DummyCharm parent) {
    ValuedTraitType[] prerequisites = new ValuedTraitType[]{new net.sf.anathema.character.main.traits.types.ValuedTraitType(AbilityType.Archery, 5)};
    ValuedTraitType essence = new net.sf.anathema.character.main.traits.types.ValuedTraitType(OtherTraitType.Essence, 3);
    CharmPrerequisiteList prerequisiteList =
            new CharmPrerequisiteList(prerequisites, essence, new String[0], new SelectiveCharmGroupTemplate[0], new IndirectCharmLearnPrerequisite[0]);
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Simple);
    CharmImpl charmImpl =
            new CharmImpl(new DummyExaltCharacterType(), "ATTRIBUTES", "Group", false, prerequisiteList, new CostList(null, null, null, null),
              new ComboRestrictions(), SimpleDuration.getDuration("Duration"),
              model, new SourceBook[0]);
    charmImpl.addParentCharms(parent);
    return charmImpl;
  }

  @Test
  public void testCharmNoSource() throws Exception {
    ValuedTraitType[] prerequisites = new ValuedTraitType[]{new net.sf.anathema.character.main.traits.types.ValuedTraitType(AbilityType.Archery, 5)};
    ValuedTraitType essence = new net.sf.anathema.character.main.traits.types.ValuedTraitType(OtherTraitType.Essence, 3);
    CharmPrerequisiteList prerequisiteList =
            new CharmPrerequisiteList(prerequisites, essence, new String[0], new SelectiveCharmGroupTemplate[0], new IndirectCharmLearnPrerequisite[0]);
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Simple);
    try {
      new CharmImpl(new DummyExaltCharacterType(), "ATTRIBUTES", "Group", false, prerequisiteList, new CostList(null, null, null, null),
              new ComboRestrictions(), SimpleDuration.getDuration("Duration"),
              model, null);
      fail();
    } catch (NullPointerException e) {
      // Nothing to do
    }
  }
}