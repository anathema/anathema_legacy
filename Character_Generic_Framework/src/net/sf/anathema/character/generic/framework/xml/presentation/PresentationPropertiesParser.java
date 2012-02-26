package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class PresentationPropertiesParser extends AbstractXmlTemplateParser<GenericPresentationTemplate> {

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

  @Override
  public GenericPresentationTemplate parseTemplate(Element element) throws PersistenceException {
    GenericPresentationTemplate basicTemplate = getBasicTemplate(element);
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
}