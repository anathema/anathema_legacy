package net.sf.anathema.character.generic.framework.xml.core;

import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IWorkingTemplateFactory<T> {

  public T getWorkingTemplateForId(IXmlTemplateRegistry<T> templateRegistry, String templateId)
      throws PersistenceException;
}