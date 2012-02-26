package net.sf.anathema.character.generic.framework.xml;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.presentation.CharmPresentationPropertiesParser;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericCharmPresentationProperties;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;

import java.awt.Color;
import java.awt.Dimension;

public class CharmPresentationPropertiesParserTest extends TestCase {

  private static final String XML = "<charmPresentation>" //$NON-NLS-1$
          + "<polygon>157.07742,9.777771</polygon>" //$NON-NLS-1$
          + "<charmDimension width=\"150\" height=\"75\"/>" //$NON-NLS-1$
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

  public void testParsePolygonString() throws Exception {
    GenericCharmPresentationProperties presentationProperties = parseXml(XML);
    assertEquals("157.07742,9.777771", presentationProperties.getNodeFramePolygonString()); //$NON-NLS-1$
  }

  public void testNoPolygon() throws Exception {
    GenericCharmPresentationProperties properties = parseXml("<charmPresentation/>"); //$NON-NLS-1$
    assertNull(properties.getNodeFramePolygonString());
  }

  public void testParseCharmDimension() throws Exception {
    GenericCharmPresentationProperties presentationProperties = parseXml(XML);
    assertEquals(new Dimension(150, 75), presentationProperties.getNodeDimension());
  }

  public void testNoCharmDimension() throws Exception {
    String challengedXml = "<charmPresentation>" //$NON-NLS-1$
            + "<polygon>157.07742,9.777771</polygon>" //$NON-NLS-1$
            + "<gapDimension width=\"25\" height=\"50\"/>" //$NON-NLS-1$
            + "<lineDimension width=\"25\" height=\"75\"/>" //$NON-NLS-1$
            + "</charmPresentation>"; //$NON-NLS-1$
    GenericCharmPresentationProperties properties = parseXml(challengedXml);
    assertNull(properties.getNodeDimension());
  }

  public void testDefaultGapDimension() throws Exception {
    GenericCharmPresentationProperties presentationProperties = parseXml(XML);
    assertEquals(new Dimension(25, 50), presentationProperties.getGapDimension());
  }

  public void testDefaultLineDimension() throws Exception {
    GenericCharmPresentationProperties presentationProperties = parseXml(XML);
    assertEquals(new Dimension(25, 75), presentationProperties.getVerticalLineDimension());
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