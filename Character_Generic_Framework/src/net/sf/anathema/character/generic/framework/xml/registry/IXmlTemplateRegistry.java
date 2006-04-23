package net.sf.anathema.character.generic.framework.xml.registry;

import net.sf.anathema.character.generic.framework.xml.ITemplateParser;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IXmlTemplateRegistry<T> {

  public abstract void setTemplateParser(ITemplateParser<T> templateParser);

  public abstract T get(String id) throws PersistenceException;
}