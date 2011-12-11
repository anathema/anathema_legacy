package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.dummy.DummyFavorableGenericTrait;
import net.sf.anathema.character.generic.dummy.DummyLimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.AlternateMinimumRestriction;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;

public class AlternateMinimumRestrictionTest extends BasicTestCase {

  public void test() throws Exception {
    AlternateMinimumRestriction restriction = new AlternateMinimumRestriction(1, 1);
    restriction.addTraitType(AbilityType.Endurance);
    restriction.addTraitType(AbilityType.Sail);
    DummyLimitationContext context = new DummyLimitationContext();
    context.addTrait(new DummyFavorableGenericTrait(AbilityType.Endurance, 3));
    context.addTrait(new DummyFavorableGenericTrait(AbilityType.Sail, 0));
    assertTrue(restriction.isFullfilledWithout(context, AbilityType.Sail));
    assertFalse(restriction.isFullfilledWithout(context, AbilityType.Endurance));
  }
}