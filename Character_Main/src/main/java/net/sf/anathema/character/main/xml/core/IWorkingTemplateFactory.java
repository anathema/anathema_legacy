package net.sf.anathema.character.main.xml.core;

import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IWorkingTemplateFactory<T> {

  T getWorkingTemplateForId(IXmlTemplateRegistry<T> templateRegistry, String templateId) throws PersistenceException;

  T getWorkingTemplateForId(IXmlTemplateRegistry<T> templateRegistry, String templateId, String prefix) throws PersistenceException;
}