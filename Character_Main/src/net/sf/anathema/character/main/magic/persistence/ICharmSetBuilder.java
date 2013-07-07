package net.sf.anathema.character.main.magic.persistence;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Document;

import java.util.List;

public interface ICharmSetBuilder {

  ICharm[] buildCharms(Document charmDoc, List<ISpecialCharm> special) throws PersistenceException;
}