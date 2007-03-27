package net.sf.anathema.character.generic.impl.magic.persistence;

import java.util.List;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;

public interface ICharmSetBuilder {

  public ICharm[] buildCharms(Document charmDoc, List<ICharm> existingCharms) throws PersistenceException;
}