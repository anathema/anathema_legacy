package net.sf.anathema.character.framework.persistence;

import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.character.framework.item.Item;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import net.sf.anathema.lib.exception.PersistenceException;

import java.io.IOException;

public interface RepositoryItemPersister {

  void save(RepositoryWriteAccess writeAccess, Item item) throws IOException, RepositoryException;

  Item load(RepositoryReadAccess readAccess) throws PersistenceException, RepositoryException;

  Item createNew(HeroTemplate template) throws PersistenceException;
}