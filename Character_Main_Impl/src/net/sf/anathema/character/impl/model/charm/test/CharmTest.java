package net.sf.anathema.character.impl.model.charm.test;

import junit.framework.TestCase;
import net.disy.commons.core.util.ContractFailedException;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.PermanentCostList;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;

public class CharmTest extends TestCase {

  public void testCharmNoSource() throws Exception {
    IGenericTrait[] prerequisites = new IGenericTrait[] { new ValuedTraitType(AbilityType.Archery, 5) };
    IGenericTrait essence = new ValuedTraitType(OtherTraitType.Essence, 3);
    CharmPrerequisiteList prerequisiteList = new CharmPrerequisiteList(
        prerequisites,
        essence,
        new String[0],
        new SelectiveCharmGroupTemplate[0],
        new ICharmAttributeRequirement[0]);
    try {
      new Charm(CharacterType.SOLAR, "ID", //$NON-NLS-1$
          "Group", //$NON-NLS-1$
          prerequisiteList,
          new CostList(null, null, null),
          new PermanentCostList(null, null, null, null),
          new ComboRestrictions(),
          Duration.getDuration("Duration"), //$NON-NLS-1$
          CharmType.Simple,
          null);
      fail();
    }
    catch (ContractFailedException e) {
      // Nothing to do
    }
  }
}