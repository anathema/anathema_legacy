package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Document;

import java.util.List;

public interface ICharmSetBuilder {

  Charm[] buildCharms(Document charmDoc, List<SpecialCharmDto> special) throws PersistenceException;
}