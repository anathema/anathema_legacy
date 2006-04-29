package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.disy.commons.core.testing.ExceptionConvertingBlock;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GroupStringBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class GroupStringBuilderTest extends BasicTestCase {

  private GroupStringBuilder builder = new GroupStringBuilder();

  public void testBuildGroupFromAttribute() throws Exception {
    String xml = "<charm group=\"group\"/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement, null);
    assertEquals("group", id); //$NON-NLS-1$
  }

  public void testBuildGroupFromPrimaryTrait() throws Exception {
    String xml = "<charm />"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    String id = builder.build(rootElement, new ValuedTraitType(AttributeType.Appearance, 5));
    assertEquals("Appearance", id); //$NON-NLS-1$
  }

  public void testBuildGroupWithoutPrimaryTrait() throws Exception {
    assertThrowsException(CharmException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        String xml = "<charm />"; //$NON-NLS-1$
        Element rootElement = DocumentUtilities.read(xml).getRootElement();
        builder.build(rootElement, null);
      }
    });
  }
}