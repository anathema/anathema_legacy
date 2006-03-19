package net.sf.anathema.character.generic.framework.xml.core;

import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class ClonedInstanceWorkingTemplateFactory<T extends ICloneable> implements IWorkingTemplateFactory<T> {

  public T getWorkingTemplateForId(IXmlTemplateRegistry<T> templateRegistry, String templateId)
      throws PersistenceException {
    return (T) templateRegistry.get(templateId).clone();
  }
}