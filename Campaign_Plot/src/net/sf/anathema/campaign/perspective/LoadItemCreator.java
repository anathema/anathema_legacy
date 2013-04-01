package net.sf.anathema.campaign.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.action.IFileProvider;
import net.sf.anathema.framework.presenter.action.IItemCreator;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

public class LoadItemCreator implements IItemCreator {

  private final IApplicationModel model;

  public LoadItemCreator(IApplicationModel model) {
    this.model = model;
  }

  @Override
  public IItem createItem(IItemType type, IDialogModelTemplate template) throws PersistenceException {
    IRepositoryItemPersister persister = model.getPersisterRegistry().get(type);
    try {
      IRepositoryReadAccess readAccess = model.getRepository().openReadAccess(type, (IFileProvider) template);
      IItem item = persister.load(readAccess);
      item.setClean();
      return item;
    } catch (RepositoryException e) {
      throw new PersistenceException("An exception occured while loading.", e); //$NON-NLS-1$
    }
  }
}