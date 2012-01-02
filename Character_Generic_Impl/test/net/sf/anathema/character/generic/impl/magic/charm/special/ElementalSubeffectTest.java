package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.dummy.DummyCasteType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ElementalSubeffectTest {

  @Test
  public void matchesCasteTypeWithSameElement() throws Exception {
    DummyCasteType type = new DummyCasteType("Water");
    ElementalSubeffect subeffect = new ElementalSubeffect(Element.Water, null, null);
    assertThat(subeffect.matches(type), is(true));
  }
}