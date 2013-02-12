package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.core.ISwingFrameOrDialog;
import net.sf.anathema.lib.gui.dialog.wizard.WizardDialog;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;
import java.util.Collection;

public abstract class AbstractItemAction extends SmartAction {

  private final IResources resources;
  private final IAnathemaModel anathemaModel;

  public AbstractItemAction(IAnathemaModel anathemaModel, IResources resources) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
  }

  protected final boolean showDialog(Component parentComponent, IAnathemaWizardPage startPage) {
    WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
    ISwingFrameOrDialog configuredDialog = dialog.getConfiguredDialog();
    configuredDialog.setResizable(false);
    GuiUtilities.centerToParent(configuredDialog.getWindow());
    IDialogResult result = dialog.show();
    return result.isCanceled();
  }

  protected IAnathemaModel getAnathemaModel() {
    return anathemaModel;
  }

  protected IResources getResources() {
    return resources;
  }

  protected static IItemType[] collectItemTypes(IAnathemaModel model) {
    IItemTypeRegistry itemTypeRegistry = model.getItemTypeRegistry();
    return getIntegratedItemTypes(itemTypeRegistry);
  }

  public static IItemType[] getIntegratedItemTypes(IItemTypeRegistry itemTypeRegistry) {
    Collection<IItemType> types = itemTypeRegistry.getIntegratedItemTypes();
    return types.toArray(new IItemType[types.size()]);
  }
}