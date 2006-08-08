package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

public class LoadItemCreator implements IItemCreator {

  private final IAnathemaModel model;

  public LoadItemCreator(IAnathemaModel model) {
    this.model = model;
  }

  public IItem createItem(IItemType type, IAnathemaWizardModelTemplate template) throws PersistenceException {
    IRepositoryItemPersister persister = model.getPersisterRegistry().get(type);
    try {
      IRepositoryReadAccess readAccess = model.getRepository().openReadAccess(type, (IFileProvider) template);
      return persister.load(readAccess);
    }
    catch (RepositoryException e) {
      throw new PersistenceException("An exception occured while loading.", e); //$NON-NLS-1$
    }
  }
}