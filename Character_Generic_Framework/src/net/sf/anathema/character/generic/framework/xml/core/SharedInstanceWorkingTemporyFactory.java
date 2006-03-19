package net.sf.anathema.character.generic.framework.xml.core;

import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;

public class SharedInstanceWorkingTemporyFactory<T> implements IWorkingTemplateFactory<T> {

  public T getWorkingTemplateForId(IXmlTemplateRegistry<T> templateRegistry, String templateId)
      throws PersistenceException {
    return templateRegistry.get(templateId);
  }
}