package net.sf.anathema.character.generic.impl.magic.persistence;

import java.util.List;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public interface ICharmBuilder {

  public Charm buildCharm(Element charmElement) throws PersistenceException;
	
  public Charm buildCharm(Element charmElement, List<ISpecialCharm> specialCharms) throws PersistenceException;
}