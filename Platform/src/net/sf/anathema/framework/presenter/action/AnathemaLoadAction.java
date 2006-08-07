package net.sf.anathema.framework.presenter.action;

import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class AnathemaLoadAction extends AbstractAnathemaItemAction {

  public static Action createMenuAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaLoadAction(model, resources);
    action.setName(resources.getString("AnathemaCore.Tools.Load.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$       
    return action;
  }

  public AnathemaLoadAction(IAnathemaModel model, IResources resources) {
    super(model, resources);
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));
  }

  @Override
  protected IItem createItem(IItemType type, IAnathemaWizardModelTemplate template) throws PersistenceException {
    IRepositoryItemPersister persister = getAnathemaModel().getPersisterRegistry().get(type);
    try {
      IRepositoryReadAccess readAccess = getAnathemaModel().getRepository().openReadAccess(
          type,
          (IFileProvider) template);
      return persister.load(readAccess);
    }
    catch (RepositoryException e) {
      throw new PersistenceException("An exception occured while loading.", e); //$NON-NLS-1$
    }
  }

  @Override
  protected IRegistry<IItemType, IWizardFactory> getFollowUpWizardFactoryRegistry() {
    Registry<IItemType, IWizardFactory> registry = new Registry<IItemType, IWizardFactory>();
    IItemType[] types = collectItemTypes(getAnathemaModel());
    IPrintNameFileAccess printNameFileAccess = getAnathemaModel().getRepository().getPrintNameFileAccess();
    IItemMangementModel itemManagement = getAnathemaModel().getItemManagement();
    for (IItemType type : types) {
      registry.register(type, new LoadItemWizardPageFactory(type, printNameFileAccess, itemManagement, getResources()));
    }
    return registry;
  }
}