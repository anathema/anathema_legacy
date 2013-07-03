package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import org.junit.Test;

import static org.junit.Assert.fail;

public class CharmTest {

  @Test
  public void testCharmNoSource() throws Exception {
    ValuedTraitType[] prerequisites = new ValuedTraitType[]{new net.sf.anathema.character.generic.traits.types.ValuedTraitType(AbilityType.Archery, 5)};
    ValuedTraitType essence = new net.sf.anathema.character.generic.traits.types.ValuedTraitType(OtherTraitType.Essence, 3);
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
