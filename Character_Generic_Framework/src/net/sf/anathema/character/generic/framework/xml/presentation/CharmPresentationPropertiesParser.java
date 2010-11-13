package net.sf.anathema.character.generic.framework.xml.presentation;

import java.awt.Dimension;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmPresentationPropertiesParser extends AbstractXmlTemplateParser<GenericCharmPresentationProperties> {

  private static final String TAG_POLYGON = "polygon"; //$NON-NLS-1$
  private static final String TAG_CHARM_DIMENSION = "charmDimension"; //$NON-NLS-1$
  private static final String ATTRIB_WIDTH = "width"; //$NON-NLS-1$
  private static final String ATTRIB_HEIGHT = "height"; //$NON-NLS-1$
  private static final String TAG_GAP_DIMENSION = "gapDimension"; //$NON-NLS-1$
  private static final String TAG_LINE_DIMENSION = "lineDimension"; //$NON-NLS-1$
  private static final String TAG_ISOLATE_SINGLE_CHARMS = "isolateSingleCharms"; //$NON-NLS-1$
  private static final String ATTRIB_ISOLATE = "isolate"; //$NON-NLS-1$

  public CharmPresentationPropertiesParser(IXmlTemplateRegistry<GenericCharmPresentationProperties> templateRegistry) {
    super(templateRegistry);
  }

  @Override
  protected GenericCharmPresentationProperties createNewBasicTemplate() {
    return new GenericCharmPresentationProperties();
  }

  public GenericCharmPresentationProperties parseTemplate(Element element) throws PersistenceException {
    GenericCharmPresentationProperties basicTemplate = getBasicTemplate(element);
    parsePolygonString(element, basicTemplate);
    parseCharmDimension(element, basicTemplate);
    parseGapDimension(element, basicTemplate);
    parseLineDimension(element, basicTemplate);
    parseIsolateSingleCharms(element, basicTemplate);
    return basicTemplate;
  }

  private void parseIsolateSingleCharms(Element element, GenericCharmPresentationProperties basicTemplate) {
    Element isolateElement = element.element(TAG_ISOLATE_SINGLE_CHARMS);
    if (isolateElement == null) {
      return;
    }
    basicTemplate.setIsolateSingles(ElementUtilities.getBooleanAttribute(isolateElement, ATTRIB_ISOLATE, false));
  }

  private void parseLineDimension(Element element, GenericCharmPresentationProperties basicTemplate)
      throws PersistenceException {
    Element dimensionElement = element.element(TAG_LINE_DIMENSION);
    if (dimensionElement == null) {
      return;
    }
    Dimension dimension = parseDimension(dimensionElement);
    basicTemplate.setVerticalLineDimension(dimension);
  }

  private void parseGapDimension(Element element, GenericCharmPresentationProperties basicTemplate)
      throws PersistenceException {
    Element dimensionElement = element.element(TAG_GAP_DIMENSION);
    if (dimensionElement == null) {
      return;
    }
    Dimension dimension = parseDimension(dimensionElement);
    basicTemplate.setGapDimension(dimension);
  }

  private void parseCharmDimension(Element element, GenericCharmPresentationProperties basicTemplate)
      throws PersistenceException {
    Element dimensionElement = element.element(TAG_CHARM_DIMENSION);
    if (dimensionElement == null) {
      return;
    }
    Dimension dimension = parseDimension(dimensionElement);
    basicTemplate.setCharmDimension(dimension);
  }

  private Dimension parseDimension(Element dimensionElement) throws PersistenceException {
    int width = ElementUtilities.getRequiredIntAttrib(dimensionElement, ATTRIB_WIDTH);
    int height = ElementUtilities.getRequiredIntAttrib(dimensionElement, ATTRIB_HEIGHT);
    return new Dimension(width, height);
  }

  private void parsePolygonString(Element element, GenericCharmPresentationProperties basicTemplate) {
    Element polygonElement = element.element(TAG_POLYGON);
    if (polygonElement == null) {
      return;
    }
    String polygonString = polygonElement.getText();
    basicTemplate.setPolygonString(polygonString);
  }
}
