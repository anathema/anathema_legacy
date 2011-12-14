package net.sf.anathema.character.generic.persistence.load.load;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GroupStringBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;
import org.junit.Test;

public class GroupStringBuilderTest {

  private GroupStringBuilder builder = new GroupStringBuilder();

  @Test
  public void testBuildGroupFromAttribute() throws Exception {
    String xml = "<charm group=\"group\"/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement, null);
    assertEquals("group", id); //$NON-NLS-1$
  }

  @Test
  public void testBuildGroupFromPrimaryTrait() throws Exception {
    String xml = "<charm />"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement, new ValuedTraitType(AttributeType.Appearance, 5));
    assertEquals("Appearance", id); //$NON-NLS-1$
  }
  @Test(expected=CharmException.class)
  public void testBuildGroupWithoutPrimaryTrait() throws Exception {
        String xml = "<charm />"; //$NON-NLS-1$
        Element rootElement = DocumentUtilities.read(xml).getRootElement();
        builder.build(rootElement, null);
  }
}