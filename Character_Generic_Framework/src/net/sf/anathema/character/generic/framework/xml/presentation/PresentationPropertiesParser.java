package net.sf.anathema.character.generic.framework.xml.presentation;

import java.awt.Color;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class PresentationPropertiesParser extends AbstractXmlTemplateParser<GenericPresentationTemplate> {

  private static final String TAG_COLOR = "color"; //$NON-NLS-1$
  private static final String TAG_CHARM_PRESENTATION = "charmPresentation";//$NON-NLS-1$
  private final AbstractXmlTemplateParser<GenericCharmPresentationProperties> charmPresentationParser;

  public PresentationPropertiesParser(
      IXmlTemplateRegistry<GenericPresentationTemplate> templateRegistry,
      IXmlTemplateRegistry<GenericCharmPresentationProperties> charmPresentationTemplateRegistry) {
    super(templateRegistry);
    this.charmPresentationParser = new CharmPresentationPropertiesParser(charmPresentationTemplateRegistry);
  }

  @Override
  protected GenericPresentationTemplate createNewBasicTemplate() {
    return new GenericPresentationTemplate();
  }

  public GenericPresentationTemplate parseTemplate(Element element) throws PersistenceException {
    GenericPresentationTemplate basicTemplate = getBasicTemplate(element);
    updateColor(element, basicTemplate);
    updateNewResources(element, basicTemplate);
    updateCharmPresentation(element, basicTemplate);
    return basicTemplate;
  }

  private void updateCharmPresentation(Element element, GenericPresentationTemplate basicTemplate)
      throws PersistenceException {
    Element charmPresentationElement = element.element(TAG_CHARM_PRESENTATION);
    if (charmPresentationElement == null) {
      return;
    }
    GenericCharmPresentationProperties properties = charmPresentationParser.parseTemplate(charmPresentationElement);
    basicTemplate.setCharmPresentationProperties(properties);
  }

  private void updateNewResources(Element parent, GenericPresentationTemplate basicTemplate) {
    Element newResourcesElement = parent.element("newResources"); //$NON-NLS-1$
    if (newResourcesElement == null) {
      return;
    }
    Element newActionElement = newResourcesElement.element("newAction"); //$NON-NLS-1$
    if (newActionElement != null) {
      basicTemplate.setNewActionResource(newActionElement.getText());
    }
  }

  private void updateColor(Element parent, GenericPresentationTemplate basicTemplate) throws PersistenceException {
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