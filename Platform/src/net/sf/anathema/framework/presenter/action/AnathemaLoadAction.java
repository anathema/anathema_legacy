package net.sf.anathema.framework.presenter.action;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.ILegalityProvider;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class AnathemaLoadAction extends AbstractAnathemaItemAction {

  public static Action createMenuAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaLoadAction(model, resources);
    action.setName(resources.getString("AnathemaCore.Tools.Load.Name") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$       
    return action;
  }

  public AnathemaLoadAction(IAnathemaModel model, IResources resources) {
    super(model, resources, new LoadItemCreator(model));
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    new LoadActionEnabler(model.getRepository(), model.getItemManagement(), this, collectItemTypes(model)).init();
  }

  @Override
  protected ILegalityProvider<IItemType> getLegalityProvider() {
    return new ILegalityProvider<IItemType>() {
      public boolean isLegal(IItemType value) {
        return getRepository().containsClosed(value);
      }
    };
  }

  private IRepository getRepository() {
    return getAnathemaModel().getRepository();
  }

  @Override
  protected IRegistry<IItemType, IWizardFactory> getFollowUpWizardFactoryRegistry() {
    Registry<IItemType, IWizardFactory> registry = new Registry<IItemType, IWizardFactory>();
    IItemType[] types = collectItemTypes(getAnathemaModel());
    IPrintNameFileAccess printNameFileAccess = getRepository().getPrintNameFileAccess();
    for (IItemType type : types) {
      ItemTypeCreationViewPropertiesExtensionPoint extension = (ItemTypeCreationViewPropertiesExtensionPoint) getAnathemaModel().getExtensionPointRegistry()
          .get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
      IItemTypeViewProperties properties = extension.get(type);
      registry.register(type, new ItemSelectionWizardPageFactory(
          type,
          printNameFileAccess,
          new LoadItemWizardProperties(getResources(), properties.getItemTypeUI())));
    }
    return registry;
  }
}