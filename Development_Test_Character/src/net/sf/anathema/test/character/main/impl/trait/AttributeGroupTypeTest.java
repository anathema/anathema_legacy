package net.sf.anathema.test.character.main.impl.trait;

import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.lib.testing.BasicTestCase;

public class AttributeGroupTypeTest extends BasicTestCase {

  public void testAll() throws Exception {
    List allTypes = Arrays.asList(AttributeGroupType.values());
    assertEquals(3, allTypes.size());
    assertTrue(allTypes.contains(AttributeGroupType.Mental));
    assertTrue(allTypes.contains(AttributeGroupType.Social));
    assertTrue(allTypes.contains(AttributeGroupType.Physical));
  }

  public void testGetById() throws Exception {
    AttributeGroupType[] groupTypes = AttributeGroupType.values();
    for (int index = 0; index < groupTypes.length; index++) {
      assertSame(groupTypes[index], AttributeGroupType.valueOf(groupTypes[index].getId()));
    }
  }
}