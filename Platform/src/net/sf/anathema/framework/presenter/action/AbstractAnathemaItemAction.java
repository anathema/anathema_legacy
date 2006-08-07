package net.sf.anathema.framework.presenter.action;

import java.awt.Component;

import net.disy.commons.core.message.Message;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.repository.creation.ItemTypeSelectionProperties;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;
import net.sf.anathema.lib.workflow.wizard.selection.ListObjectSelectionPageView;
import net.sf.anathema.lib.workflow.wizard.selection.ObjectSelectionWizardModel;
import net.sf.anathema.lib.workflow.wizard.selection.ObjectSelectionWizardPage;

public abstract class AbstractAnathemaItemAction extends AbstractItemAction {

  public AbstractAnathemaItemAction(IAnathemaModel anathemaModel, IResources resources) {
    super(anathemaModel, resources);
  }

  @Override
  protected final void execute(Component parentComponent) {
    try {
      ObjectSelectionWizardModel<IItemType> model = new ObjectSelectionWizardModel<IItemType>(
          collectItemTypes(getAnathemaModel()));
      IRegistry<IItemType, IWizardFactory> followUpWizardFactoryRegistry = getFollowUpWizardFactoryRegistry();
      Registry<IItemType, IAnathemaWizardModelTemplate> registry = createModelTemplateRegistry(followUpWizardFactoryRegistry);
      IAnathemaWizardPage startPage = new ObjectSelectionWizardPage<IItemType>(
          followUpWizardFactoryRegistry,
          registry,
          model,
          new ListObjectSelectionPageView<IItemType>(IItemType.class),
          new ItemTypeSelectionProperties(getResources()));
      boolean canceled = showDialog(parentComponent, startPage);
      if (canceled) {
        return;
      }
      IItemType type = model.getSelectedObject();
      IItem item = createItem(type, registry.get(type));
      createView(parentComponent, item);
    }
    catch (Exception e) {
      Message message = new Message(getResources().getString("AnathemaPersistence.NewWizard.Message.Error"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(AnathemaNewAction.class, parentComponent, message);
    }
  }

  protected abstract IItem createItem(IItemType type, IAnathemaWizardModelTemplate template)
      throws PersistenceException;

  private Registry<IItemType, IAnathemaWizardModelTemplate> createModelTemplateRegistry(
      IRegistry<IItemType, IWizardFactory> followUpWizardFactoryRegistry) {
    Registry<IItemType, IAnathemaWizardModelTemplate> registry = new Registry<IItemType, IAnathemaWizardModelTemplate>();
    for (IItemType type : collectItemTypes(getAnathemaModel())) {
      registry.register(type, followUpWizardFactoryRegistry.get(type).createTemplate());
    }
    return registry;
  }

  protected abstract IRegistry<IItemType, IWizardFactory> getFollowUpWizardFactoryRegistry();
}