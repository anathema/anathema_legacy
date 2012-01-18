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
      + "<polygon>157.07742,9.777771</polygon>" //$NON-NLS-1$
      + "<charmDimension width=\"150\" height=\"75\"/>" //$NON-NLS-1$
      + "<gapDimension width=\"25\" height=\"50\"/>" //$NON-NLS-1$
      + "<lineDimension width=\"25\" height=\"75\"/>" //$NON-NLS-1$
      + "</charmPresentation>"; //$NON-NLS-1$
  private CharmPresentationPropertiesParser parser;
  private DummyXmlTemplateRegistry<GenericCharmPresentationProperties> templateRegistry;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.templateRegistry = new DummyXmlTemplateRegistry<GenericCharmPresentationProperties>();
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

  public void testParseGapDimension() throws Exception {
    GenericCharmPresentationProperties presentationProperties = parseXml(XML);
    assertEquals(new Dimension(25, 50), presentationProperties.getGapDimension());
  }

  public void testNoGapDimensionThrowsException() throws Exception {
    String crippleXML = "<charmPresentation>" //$NON-NLS-1$
        + "<polygon>157.07742,9.777771</polygon>" //$NON-NLS-1$
        + "<charmDimension width=\"150\" height=\"75\"/>" //$NON-NLS-1$
        + "<lineDimension width=\"25\" height=\"75\"/>" //$NON-NLS-1$
        + "</charmPresentation>"; //$NON-NLS-1$
    GenericCharmPresentationProperties properties = parseXml(crippleXML);
    assertNull(properties.getGapDimension());
  }

  public void testParseLineDimension() throws Exception {
    GenericCharmPresentationProperties presentationProperties = parseXml(XML);
    assertEquals(new Dimension(25, 75), presentationProperties.getVerticalLineDimension());
  }

  public void testNoLineDimensionThrowsException() throws Exception {
    String crippleXML = "<charmPresentation>" //$NON-NLS-1$
        + "<polygon>157.07742,9.777771</polygon>" //$NON-NLS-1$
        + "<charmDimension width=\"150\" height=\"75\"/>" //$NON-NLS-1$
        + "<gapDimension width=\"25\" height=\"50\"/>" //$NON-NLS-1$
        + "</charmPresentation>"; //$NON-NLS-1$
    GenericCharmPresentationProperties properties = parseXml(crippleXML);
    assertNull(properties.getVerticalLineDimension());
  }

  public void testNoIsolateSingles() throws Exception {
    GenericCharmPresentationProperties properties = parseXml(XML);
    assertFalse(properties.isolateSingles());
  }

  public void testIsolateSingles() throws Exception {
    String isolateXml = "<charmPresentation>" //$NON-NLS-1$
        + "<polygon>157.07742,9.777771</polygon>" //$NON-NLS-1$
        + "<charmDimension width=\"150\" height=\"75\"/>" //$NON-NLS-1$
        + "<gapDimension width=\"25\" height=\"50\"/>" //$NON-NLS-1$
        + "<lineDimension width=\"25\" height=\"75\"/>" //$NON-NLS-1$
        + "<isolateSingleCharms isolate=\"true\"/>" //$NON-NLS-1$
        + "</charmPresentation>"; //$NON-NLS-1$
    GenericCharmPresentationProperties properties = parseXml(isolateXml);
    assertTrue(properties.isolateSingles());
  }
}