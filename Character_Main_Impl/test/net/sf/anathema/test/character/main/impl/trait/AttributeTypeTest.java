package net.sf.anathema.test.character.main.impl.trait;

import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AttributeTypeTest {

  @Test
  public void testAll() throws Exception {
    List<AttributeType> allTypes = Arrays.asList(AttributeType.values());
    assertEquals(9, allTypes.size());
    assertTrue(allTypes.contains(AttributeType.Strength));
    assertTrue(allTypes.contains(AttributeType.Dexterity));
    assertTrue(allTypes.contains(AttributeType.Stamina));
    assertTrue(allTypes.contains(AttributeType.Charisma));
    assertTrue(allTypes.contains(AttributeType.Manipulation));
    assertTrue(allTypes.contains(AttributeType.Appearance));
    assertTrue(allTypes.contains(AttributeType.Perception));
    assertTrue(allTypes.contains(AttributeType.Intelligence));
    assertTrue(allTypes.contains(AttributeType.Wits));
  }

  @Test
  public void testGetById() throws Exception {
    AttributeType[] attributeTypes = AttributeType.values();
    for (AttributeType attributeType : attributeTypes) {
      assertSame(attributeType, AttributeType.valueOf(attributeType.getId()));
    }
  }

  @Test
  public void testGetPhysicalGroup() throws Exception {
    List<AttributeType> allTypes = Arrays.asList(AttributeType.getAllFor(AttributeGroupType.Physical));
    assertEquals(3, allTypes.size());
    assertTrue(allTypes.contains(AttributeType.Strength));
    assertTrue(allTypes.contains(AttributeType.Dexterity));
    assertTrue(allTypes.contains(AttributeType.Stamina));
  }

  @Test
  public void testGetSocialGroup() throws Exception {
    List<AttributeType> allTypes = Arrays.asList(AttributeType.getAllFor(AttributeGroupType.Social));
    assertEquals(3, allTypes.size());
    assertTrue(allTypes.contains(AttributeType.Charisma));
    assertTrue(allTypes.contains(AttributeType.Manipulation));
    assertTrue(allTypes.contains(AttributeType.Appearance));
  }

  @Test
  public void testGetMentalGroup() throws Exception {
    List<AttributeType> allTypes = Arrays.asList(AttributeType.getAllFor(AttributeGroupType.Mental));
    assertEquals(3, allTypes.size());
    assertTrue(allTypes.contains(AttributeType.Perception));
    assertTrue(allTypes.contains(AttributeType.Intelligence));
    assertTrue(allTypes.contains(AttributeType.Wits));
  }
}