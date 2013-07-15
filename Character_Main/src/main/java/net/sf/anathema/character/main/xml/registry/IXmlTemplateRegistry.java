package net.sf.anathema.character.main.xml.registry;

import net.sf.anathema.character.main.xml.ITemplateParser;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.resources.ResourceFile;

public interface IXmlTemplateRegistry<T> {

  void setTemplateParser(ITemplateParser<T> templateParser);

  T get(String id) throws PersistenceException;

  T get(String id, String prefix) throws PersistenceException;

  T get(ResourceFile resource) throws PersistenceException;
}