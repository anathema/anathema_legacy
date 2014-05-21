package net.sf.anathema.character.magic.parser.charms;

import net.sf.anathema.character.magic.charm.CharmImpl;
import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import java.util.List;

public interface ICharmBuilder {

  CharmImpl buildCharm(Element charmElement, List<SpecialCharmDto> specialCharms) throws PersistenceException;
}