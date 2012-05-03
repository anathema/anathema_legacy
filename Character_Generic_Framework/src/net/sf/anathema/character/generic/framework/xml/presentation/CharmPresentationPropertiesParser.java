package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.awt.*;

public class CharmPresentationPropertiesParser extends AbstractXmlTemplateParser<GenericCharmPresentationProperties> {
  private static final String TAG_COLOR = "color"; //$NON-NLS-1$

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
    parseColor(element, basicTemplate);
    return basicTemplate;
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