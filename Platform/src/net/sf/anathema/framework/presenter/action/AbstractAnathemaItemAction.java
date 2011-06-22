package net.sf.anathema.framework.presenter.action;

import java.awt.Component;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.repository.creation.ItemTypeSelectionProperties;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.ILegalityProvider;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;
import net.sf.anathema.lib.workflow.wizard.selection.ListObjectSelectionPageView;
import net.sf.anathema.lib.workflow.wizard.selection.ObjectSelectionWizardModel;
import net.sf.anathema.lib.workflow.wizard.selection.ObjectSelectionWizardPage;

public abstract class AbstractAnathemaItemAction extends AbstractItemAction {

  private static final long serialVersionUID = -6862758539173646279L;
  private final IItemOperator itemOperator;

  public AbstractAnathemaItemAction(IAnathemaModel anathemaModel, IResources resources, IItemCreator creator) {
    this(anathemaModel, resources, new ItemCreationOperator(creator, resources, anathemaModel.getItemManagement()));
  }

  public AbstractAnathemaItemAction(IAnathemaModel anathemaModel, IResources resources, IItemOperator operator) {
    super(anathemaModel, resources);
    this.itemOperator = operator;
  }

  @Override
  protected final void execute(Component parentComponent) {
    try {
      ObjectSelectionWizardModel<IItemType> model = new ObjectSelectionWizardModel<IItemType>(
          collectItemTypes(getAnathemaModel()),
          getLegalityProvider());
      IRegistry<IItemType, IWizardFactory> followUpWizardFactoryRegistry = getFollowUpWizardFactoryRegistry();
      Registry<IItemType, IAnathemaWizardModelTemplate> registry = createModelTemplateRegistry(followUpWizardFactoryRegistry);
      IAnathemaWizardPage startPage = new ObjectSelectionWizardPage<IItemType>(
          followUpWizardFactoryRegistry,
          registry,
          model,
          new ListObjectSelectionPageView<IItemType>(IItemType.class),
          createSelectionProperties());
      boolean canceled = showDialog(parentComponent, startPage);
      if (canceled) {
        return;
      }
      IItemType type = model.getSelectedObject();
      itemOperator.operate(parentComponent, type, registry.get(type));
    }
    catch (Exception e) {
      Message message = new Message(getResources().getString("AnathemaPersistence.NewWizard.Message.Error"), e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(AnathemaNewAction.class, parentComponent, message);
    }
  }

  protected ItemTypeSelectionProperties createSelectionProperties() {
    final ItemTypeCreationViewPropertiesExtensionPoint extension = (ItemTypeCreationViewPropertiesExtensionPoint) getAnathemaModel().getExtensionPointRegistry()
        .get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
    IObjectUi objectUi = new ItemTypeUi(getResources(), extension);
    return new ItemTypeSelectionProperties(getResources(), getLegalityProvider(), objectUi);
  }

  private Registry<IItemType, IAnathemaWizardModelTemplate> createModelTemplateRegistry(
      IRegistry<IItemType, IWizardFactory> followUpWizardFactoryRegistry) {
    Registry<IItemType, IAnathemaWizardModelTemplate> registry = new Registry<IItemType, IAnathemaWizardModelTemplate>();
    for (IItemType type : collectItemTypes(getAnathemaModel())) {
      registry.register(type, followUpWizardFactoryRegistry.get(type).createTemplate());
    }
    return registry;
  }

  protected abstract ILegalityProvider<IItemType> getLegalityProvider();

  protected abstract IRegistry<IItemType, IWizardFactory> getFollowUpWizardFactoryRegistry();
}