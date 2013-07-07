package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.main.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.main.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.main.magic.persistence.prerequisite.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.main.magic.charms.ComboRestrictions;
import net.sf.anathema.character.main.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.main.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.main.magic.charms.type.CharmType;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import org.junit.Test;

import static org.junit.Assert.fail;

public class CharmTest {

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
