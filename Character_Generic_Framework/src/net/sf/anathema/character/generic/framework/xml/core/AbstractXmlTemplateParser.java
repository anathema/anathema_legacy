package net.sf.anathema.character.generic.framework.xml.core;

import net.sf.anathema.character.generic.framework.xml.ITemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.clone.ICloneable;

import org.dom4j.Element;

public abstract class AbstractXmlTemplateParser<T extends ICloneable<T>> implements ITemplateParser<T> {

  private final IXmlTemplateRegistry<T> templateRegistry;

  private static final String ATTRIB_USES = "uses"; //$NON-NLS-1$

  private final IWorkingTemplateFactory<T> workingTemplateFactory = new ClonedInstanceWorkingTemplateFactory<T>();

  public AbstractXmlTemplateParser(IXmlTemplateRegistry<T> templateRegistry) {
    this.templateRegistry = templateRegistry;
    templateRegistry.setTemplateParser(this);
  }

  protected final T getBasicTemplate(Element element) throws PersistenceException {
    return getBasicTemplate(element.attributeValue(ATTRIB_USES));
  }

  protected final T getBasicTemplate(String templateId) throws PersistenceException {
    T template;
    if (templateId == null) {
      template = createNewBasicTemplate();
    }
    else {
      template = workingTemplateFactory.getWorkingTemplateForId(templateRegistry, templateId);
    }
    if (template == null) {
      throw new PersistenceException("Template not found: " + templateId); //$NON-NLS-1$
    }
    return template;
  }

  protected abstract T createNewBasicTemplate();
}