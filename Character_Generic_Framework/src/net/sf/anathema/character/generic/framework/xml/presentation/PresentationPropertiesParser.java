package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.awt.*;

public class PresentationPropertiesParser extends AbstractXmlTemplateParser<GenericPresentationTemplate> {

  private static final String TAG_COLOR = "color";

  public PresentationPropertiesParser(IXmlTemplateRegistry<GenericPresentationTemplate> templateRegistry) {
    super(templateRegistry);
  }

  @Override
  protected GenericPresentationTemplate createNewBasicTemplate() {
    return new GenericPresentationTemplate();
  }

  @Override
  public GenericPresentationTemplate parseTemplate(Element element) throws PersistenceException {
    GenericPresentationTemplate basicTemplate = getBasicTemplate(element);
    updateColor(element, basicTemplate);
    return basicTemplate;
  }

  private void updateColor(Element parent, GenericPresentationTemplate basicTemplate) throws PersistenceException {
    Element colorElement = parent.element(TAG_COLOR);
    if (colorElement == null) {
      return;
    }
    int red = ElementUtilities.getRequiredIntAttrib(colorElement, "red"); //$NON-NLS-1$
    int green = ElementUtilities.getRequiredIntAttrib(colorElement, "green"); //$NON-NLS-1$
    int blue = ElementUtilities.getRequiredIntAttrib(colorElement, "blue"); //$NON-NLS-1$
    Color color = new Color(red, green, blue);
    basicTemplate.getCharmPresentationProperties().setColor(color);
  }
}