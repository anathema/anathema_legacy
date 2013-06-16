package net.sf.anathema.character.main.templateparser;

import junit.framework.TestCase;
import net.sf.anathema.character.main.testing.dummy.DummyGenericTrait;
import net.sf.anathema.character.main.testing.dummy.DummyLimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.AlternateMinimumRestriction;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class AlternateMinimumRestrictionTest extends TestCase {

  public void test() throws Exception {
    AlternateMinimumRestriction restriction = new AlternateMinimumRestriction(1, 1);
    restriction.addTraitType(AbilityType.Resistance);
    restriction.addTraitType(AbilityType.Sail);
    DummyLimitationContext context = new DummyLimitationContext();
    context.addTrait(new DummyGenericTrait(AbilityType.Resistance, 3));
    context.addTrait(new DummyGenericTrait(AbilityType.Sail, 0));
    assertTrue(restriction.isFullfilledWithout(context, AbilityType.Sail));
    assertFalse(restriction.isFullfilledWithout(context, AbilityType.Resistance));
  }
}