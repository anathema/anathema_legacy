package net.sf.anathema.character.generic.framework.xml;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.presentation.CharmPresentationPropertiesParser;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericCharmPresentationProperties;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;

import java.awt.*;

public class CharmPresentationPropertiesParserTest extends TestCase {

  private static final String XML = "<charmPresentation>" //$NON-NLS-1$
          + "<color red=\"111\" green=\"133\" blue=\"255\" />" //$NON-NLS-1$
          + "</charmPresentation>"; //$NON-NLS-1$
  private CharmPresentationPropertiesParser parser;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyXmlTemplateRegistry<GenericCharmPresentationProperties> templateRegistry = new DummyXmlTemplateRegistry<GenericCharmPresentationProperties>();
    this.parser = new CharmPresentationPropertiesParser(templateRegistry);
  }

  private GenericCharmPresentationProperties parseXml(String xmlCode) throws AnathemaException {
    Element templateElement = DocumentUtilities.read(xmlCode).getRootElement();
    return parser.parseTemplate(templateElement);
  }

  public void testPolygonIsFixed() throws Exception {
    GenericCharmPresentationProperties presentationProperties = parseXml(XML);
    assertEquals(
            "5.5,11.3542 35.3236,11.3542 30.24724,3.5 155.2527,3.5 150.17636,11.3542 180.0,11.3542 180.0,82.64578 150.17636,82.64578 155.2527,90.5 30.24724,90.5 35.3236,82.64578 5.5,82.64578",
            presentationProperties.getNodeFramePolygonString()); //$NON-NLS-1$
  }

  public void testCharmDimensionIsFixed() throws Exception {
    GenericCharmPresentationProperties properties = parseXml(XML);
    assertEquals(new Dimension(180, 90), properties.getNodeDimension());
  }

  public void testDefaultGapDimension() throws Exception {
    GenericCharmPresentationProperties presentationProperties = parseXml(XML);
    assertEquals(new Dimension(25, 50), presentationProperties.getGapDimension());
  }

  public void testDefaultLineDimension() throws Exception {
    GenericCharmPresentationProperties presentationProperties = parseXml(XML);
    assertEquals(new Dimension(25, 90), presentationProperties.getVerticalLineDimension());
  }

  public void testParseXmlWithoutColor() throws Exception {
    String xml = "<charmPresentation />"; //$NON-NLS-1$
    GenericCharmPresentationProperties presentationProperties = parseXml(xml);
    assertNull(presentationProperties.getColor());
  }

  public void testColorUpdateWithParsing() throws Exception {
    GenericCharmPresentationProperties presentationProperties = parseXml(XML);
    assertEquals(new Color(111, 133, 255), presentationProperties.getColor());
  }
}