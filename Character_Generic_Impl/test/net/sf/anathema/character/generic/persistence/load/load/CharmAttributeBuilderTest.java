package net.sf.anathema.character.generic.persistence.load.load;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CharmAttributeBuilder;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CharmAttributeBuilderTest {

  @Test
  public void testGenericAttributes() throws Exception {
    String xml = "<charm><genericCharmAttribute attribute=\"test\"/></charm>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    ICharmAttribute[] attribute = new CharmAttributeBuilder().buildCharmAttributes(rootElement, new ValuedTraitType(
        AbilityType.Brawl,
        3));
    assertTrue(ArrayUtilities.contains(attribute, new CharmAttribute("testBrawl", false))); //$NON-NLS-1$
  }
}