package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import java.util.List;

public interface ICharmBuilder {

  CharmImpl buildCharm(Element charmElement) throws PersistenceException;

  CharmImpl buildCharm(Element charmElement, List<ISpecialCharm> specialCharms) throws PersistenceException;
}