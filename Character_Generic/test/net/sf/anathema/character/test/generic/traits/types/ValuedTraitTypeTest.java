package net.sf.anathema.character.test.generic.traits.types;

import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValuedTraitTypeTest {

  @Test
  public void testIdenticalObjectEquals() throws Exception {
    ValuedTraitType original = new ValuedTraitType(AbilityType.Archery, 2);
    assertTrue(original.equals(original));
  }

  @Test
  public void testOtherClassDoesntEqual() throws Exception {
    assertFalse(new ValuedTraitType(AbilityType.Archery, 2).equals("Hallo?")); //$NON-NLS-1$
  }

  @Test
  public void testIdenticalNullObjectsEqual() throws Exception {
    ValuedTraitType original = ValuedTraitType.NULL_TYPE;
    assertTrue(original.equals(original));
  }

  @Test
  public void testEqualObject() throws Exception {
    ValuedTraitType original = new ValuedTraitType(AbilityType.Archery, 2);
    ValuedTraitType copy = new ValuedTraitType(AbilityType.Archery, 2);
    assertTrue(original.equals(copy));
    assertTrue(copy.equals(original));
  }

  @Test
  public void testDifferentType() throws Exception {
    ValuedTraitType original = new ValuedTraitType(AbilityType.Archery, 2);
    ValuedTraitType copy = new ValuedTraitType(AbilityType.Athletics, 2);
    assertFalse(original.equals(copy));
    assertFalse(copy.equals(original));
  }

  @Test
  public void testDifferentValue() throws Exception {
    ValuedTraitType original = new ValuedTraitType(AbilityType.Archery, 2);
    ValuedTraitType copy = new ValuedTraitType(AbilityType.Archery, 3);
    assertFalse(original.equals(copy));
    assertFalse(copy.equals(original));
  }
}