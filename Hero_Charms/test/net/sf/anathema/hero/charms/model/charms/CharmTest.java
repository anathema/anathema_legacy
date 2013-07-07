package net.sf.anathema.hero.charms.model.charms;

import net.sf.anathema.character.main.magic.Charm;
import net.sf.anathema.character.main.magic.CostList;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.magic.charms.ComboRestrictions;
import net.sf.anathema.character.main.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.main.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.main.magic.charms.type.CharmType;
import net.sf.anathema.character.main.magic.charms.type.CharmTypeModel;
import net.sf.anathema.character.main.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.main.magic.persistence.prerequisite.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.main.testing.dummy.DummyCharmData;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CharmTest {

  @Test
  public void testParentCharmsNotOverwritten() throws Exception {
    DummyCharmData data = new DummyCharmData();
    DummyCharm dummy = new DummyCharm("OtherDummy");
    data.setParentCharms(new ICharm[]{dummy});
    Charm charm = new Charm(data);
    charm.extractParentCharms(new HashMap<String, Charm>());
    assertEquals(1, charm.getParentCharms().size());
    assertEquals(dummy, charm.getParentCharms().toArray(new ICharm[1])[0]);
  }

  @Test
  public void testCharmNoSource() throws Exception {
    ValuedTraitType[] prerequisites = new ValuedTraitType[]{new net.sf.anathema.character.main.traits.types.ValuedTraitType(AbilityType.Archery, 5)};
    ValuedTraitType essence = new net.sf.anathema.character.main.traits.types.ValuedTraitType(OtherTraitType.Essence, 3);
    CharmPrerequisiteList prerequisiteList =
            new CharmPrerequisiteList(prerequisites, essence, new String[0], new SelectiveCharmGroupTemplate[0], new IndirectCharmRequirement[0]);
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Simple);
    try {
      new Charm(new DummyExaltCharacterType(), "ATTRIBUTES",
              "Group",
              false, prerequisiteList, new CostList(null, null, null, null), new ComboRestrictions(), SimpleDuration.getDuration("Duration"),

              model, null);
      fail();
    } catch (NullPointerException e) {
      // Nothing to do
    }
  }
}