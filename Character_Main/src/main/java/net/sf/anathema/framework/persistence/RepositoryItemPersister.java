package net.sf.anathema.framework.persistence;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.framework.item.Item;
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