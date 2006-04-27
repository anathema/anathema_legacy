package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.disy.commons.core.testing.ExceptionConvertingBlock;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CharmTypeBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class CharmTypeBuilderTest extends BasicTestCase {

  private CharmTypeBuilder builder = new CharmTypeBuilder();

  public void testTypeRequired() throws Exception {
    assertThrowsException(CharmException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        String xml = "<charm></charm>"; //$NON-NLS-1$
        Element element = DocumentUtilities.read(xml).getRootElement();
        builder.build(element);
      }
    });
  }

  public void testMangledType() throws Exception {
    assertThrowsException(CharmException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        String xml = "<charm><charmtype type=\"simp\"/></charm>"; //$NON-NLS-1$
        Element element = DocumentUtilities.read(xml).getRootElement();
        builder.build(element);
      }
    });
  }

  public void testBuildSimpleCharm() throws Exception {
    String xml = "<charm><charmtype type=\"Simple\"/></charm>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    CharmType type = builder.build(element);
    assertEquals(CharmType.Simple, type);
  }
}