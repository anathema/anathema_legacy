package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.character.main.magic.model.magic.attribute.MagicAttributeImpl;
import net.sf.anathema.character.main.magic.model.magic.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.parser.charms.CharmAttributeBuilder;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.ValuedTraitType;
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
    MagicAttribute[] attribute = new CharmAttributeBuilder().buildCharmAttributes(rootElement, new ValuedTraitType(AbilityType.MartialArts, 3));
    assertTrue(ArrayUtils.contains(attribute, new MagicAttributeImpl("testMartialArts", false)));
  }
}