package net.sf.anathema.character.main.xml.presentation;

import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class PresentationPropertiesParser extends AbstractXmlTemplateParser<GenericPresentationTemplate> {

  public PresentationPropertiesParser(IXmlTemplateRegistry<GenericPresentationTemplate> templateRegistry) {
    super(templateRegistry);
  }

  @Override
  protected GenericPresentationTemplate createNewBasicTemplate() {
    return new GenericPresentationTemplate();
  }

  @Override
  public GenericPresentationTemplate parseTemplate(Element element) throws PersistenceException {
    return getBasicTemplate(element);
  }
}