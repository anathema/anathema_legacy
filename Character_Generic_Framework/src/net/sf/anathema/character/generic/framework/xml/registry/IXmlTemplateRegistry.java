package net.sf.anathema.character.generic.framework.xml.registry;

import net.sf.anathema.character.generic.framework.xml.ITemplateParser;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IXmlTemplateRegistry<T> {

  void setTemplateParser(ITemplateParser<T> templateParser);

  T get(String id) throws PersistenceException;
  T get(String id, String prefix) throws PersistenceException;
}