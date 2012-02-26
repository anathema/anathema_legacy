package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.awt.Color;
import java.awt.Dimension;

public class CharmPresentationPropertiesParser extends AbstractXmlTemplateParser<GenericCharmPresentationProperties> {
  private static final String TAG_COLOR = "color"; //$NON-NLS-1$
  private static final String TAG_POLYGON = "polygon"; //$NON-NLS-1$
  private static final String TAG_CHARM_DIMENSION = "charmDimension"; //$NON-NLS-1$
  private static final String ATTRIB_WIDTH = "width"; //$NON-NLS-1$
  private static final String ATTRIB_HEIGHT = "height"; //$NON-NLS-1$

  public CharmPresentationPropertiesParser(IXmlTemplateRegistry<GenericCharmPresentationProperties> templateRegistry) {
    super(templateRegistry);
  }

  @Override
  protected GenericCharmPresentationProperties createNewBasicTemplate() {
    return new GenericCharmPresentationProperties();
  }

  @Override
  public GenericCharmPresentationProperties parseTemplate(Element element) throws PersistenceException {
    GenericCharmPresentationProperties basicTemplate = getBasicTemplate(element);
    parsePolygonString(element, basicTemplate);
    parseCharmDimension(element, basicTemplate);
    parseColor(element, basicTemplate);
    return basicTemplate;
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

  private void parseColor(Element parent, GenericCharmPresentationProperties basicTemplate) throws PersistenceException {
    Element colorElement = parent.element(TAG_COLOR);
    if (colorElement == null) {
      return;
    }
    int red = ElementUtilities.getRequiredIntAttrib(colorElement, "red"); //$NON-NLS-1$
    int green = ElementUtilities.getRequiredIntAttrib(colorElement, "green"); //$NON-NLS-1$
    int blue = ElementUtilities.getRequiredIntAttrib(colorElement, "blue"); //$NON-NLS-1$
    basicTemplate.setColor(new Color(red, green, blue));
  }
}