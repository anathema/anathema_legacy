package net.sf.anathema.test.character.generic.type;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.testing.BasicTestCase;

public class CharacterTypeTest extends BasicTestCase {

  public void testSerialization() throws Exception {
    for (CharacterType type : CharacterType.values()) {
      assertSame(type, assertSerialization(type));
    }
  }
}