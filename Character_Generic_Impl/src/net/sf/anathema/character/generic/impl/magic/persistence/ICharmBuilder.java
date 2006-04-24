package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public interface ICharmBuilder {

  public Charm buildCharm(Element charmElement, boolean powerCombat) throws PersistenceException;
}