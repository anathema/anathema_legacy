package net.sf.anathema.character.generic.persistence.load.load;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CharmAttributeBuilder;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.apache.commons.lang3.ArrayUtils;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CharmAttributeBuilderTest {

  @Test
  public void testGenericAttributes() throws Exception {
    String xml = "<charm><genericCharmAttribute attribute=\"test\"/></charm>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    ICharmAttribute[] attribute = new CharmAttributeBuilder().buildCharmAttributes(rootElement, new ValuedTraitType(
        AbilityType.MartialArts,
        3));
    assertTrue(ArrayUtils.contains(attribute, new CharmAttribute("testMartialArts", false)));
  }
}