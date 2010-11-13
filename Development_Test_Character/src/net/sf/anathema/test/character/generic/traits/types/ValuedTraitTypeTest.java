package net.sf.anathema.test.character.generic.traits.types;

import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.lib.testing.BasicTestCase;

public class ValuedTraitTypeTest extends BasicTestCase {

  public void testIdenticalObjectEquals() throws Exception {
    ValuedTraitType original = new ValuedTraitType(AbilityType.Archery, 2);
    assertTrue(original.equals(original));
  }

  public void testOtherClassDoesntEqual() throws Exception {
    assertFalse(new ValuedTraitType(AbilityType.Archery, 2).equals("Hallo?")); //$NON-NLS-1$
  }

  public void testIdenticalNullObjectsEqual() throws Exception {
    ValuedTraitType original = ValuedTraitType.NULL_TYPE;
    ValuedTraitType copy = original;
    assertTrue(original.equals(copy));
    assertTrue(copy.equals(original));
  }

  public void testEqualObject() throws Exception {
    ValuedTraitType original = new ValuedTraitType(AbilityType.Archery, 2);
    ValuedTraitType copy = new ValuedTraitType(AbilityType.Archery, 2);
    assertTrue(original.equals(copy));
    assertTrue(copy.equals(original));
  }

  public void testDifferentType() throws Exception {
    ValuedTraitType original = new ValuedTraitType(AbilityType.Archery, 2);
    ValuedTraitType copy = new ValuedTraitType(AbilityType.Athletics, 2);
    assertFalse(original.equals(copy));
    assertFalse(copy.equals(original));
  }

  public void testDifferentValue() throws Exception {
    ValuedTraitType original = new ValuedTraitType(AbilityType.Archery, 2);
    ValuedTraitType copy = new ValuedTraitType(AbilityType.Archery, 3);
    assertFalse(original.equals(copy));
    assertFalse(copy.equals(original));
  }
}