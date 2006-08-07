package net.sf.anathema.framework.presenter.action;

import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.ILegalityProvider;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;
import net.sf.anathema.lib.workflow.wizard.selection.LenientLegalityProvider;

public class AnathemaNewAction extends AbstractAnathemaItemAction {

  public static Action createMenuAction(IAnathemaModel anathemaModel, IResources resources) {
    SmartAction action = new AnathemaNewAction(anathemaModel, resources);
    action.setName(resources.getString("AnathemaCore.Tools.New.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$       
    return action;
  }

  public AnathemaNewAction(IAnathemaModel anathemaModel, IResources resources) {
    super(anathemaModel, resources);
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
  }

  @Override
  protected ILegalityProvider<IItemType> getLegalityProvider() {
    return new LenientLegalityProvider<IItemType>();
  }

  @Override
  protected IItem createItem(IItemType type, IAnathemaWizardModelTemplate template) throws PersistenceException {
    IRepositoryItemPersister persister = getAnathemaModel().getPersisterRegistry().get(type);
    return persister.createNew(template);
  }

  @Override
  protected IRegistry<IItemType, IWizardFactory> getFollowUpWizardFactoryRegistry() {
    return getAnathemaModel().getNewItemWizardFactoryRegistry();
  }
}