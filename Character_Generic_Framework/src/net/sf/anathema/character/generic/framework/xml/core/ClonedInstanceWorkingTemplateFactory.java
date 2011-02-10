package net.sf.anathema.character.generic.framework.xml.core;

import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class ClonedInstanceWorkingTemplateFactory<T extends ICloneable<T>> implements IWorkingTemplateFactory<T> {

  public T getWorkingTemplateForId(IXmlTemplateRegistry<T> templateRegistry, String templateId)
      throws PersistenceException {
    return templateRegistry.get(templateId).clone();
  }
  public T getWorkingTemplateForId(IXmlTemplateRegistry<T> templateRegistry, String templateId, String prefix)
      throws PersistenceException {
    return templateRegistry.get(templateId, prefix).clone();
  }
}