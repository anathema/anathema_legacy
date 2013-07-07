package net.sf.anathema.character.main.magic.persistence;

import net.sf.anathema.character.main.magic.Charm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import java.util.List;

public interface ICharmBuilder {

  Charm buildCharm(Element charmElement) throws PersistenceException;

  Charm buildCharm(Element charmElement, List<ISpecialCharm> specialCharms) throws PersistenceException;
}