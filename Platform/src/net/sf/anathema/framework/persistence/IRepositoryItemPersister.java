package net.sf.anathema.framework.persistence;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

import java.io.IOException;

public interface IRepositoryItemPersister {

  void save(IRepositoryWriteAccess writeAccess, IItem item) throws IOException, RepositoryException;

  IItem load(IRepositoryReadAccess readAccess) throws PersistenceException, RepositoryException;

  IItem createNew(IAnathemaWizardModelTemplate template) throws PersistenceException;
}