package net.sf.anathema.framework.presenter.action;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.ISwingFrameOrDialog;
import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.disy.commons.swing.util.GuiUtilities;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractItemAction extends SmartAction {

  private static final long serialVersionUID = -6505216092259346812L;
  private final IResources resources;
  private final IAnathemaModel anathemaModel;

  public AbstractItemAction(IAnathemaModel anathemaModel, IResources resources) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
  }

  protected final boolean showDialog(Component parentComponent, IAnathemaWizardPage startPage) {
    WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
    final ISwingFrameOrDialog configuredDialog = dialog.getConfiguredDialog();
    configuredDialog.setResizable(false);
    GuiUtilities.centerToParent(configuredDialog.getWindow());
    configuredDialog.show();
    return dialog.isCanceled();
  }

  protected IAnathemaModel getAnathemaModel() {
    return anathemaModel;
  }

  protected IResources getResources() {
    return resources;
  }

  protected static IItemType[] collectItemTypes(IAnathemaModel model) {
    List<IItemType> types = new ArrayList<IItemType>();
    for (IItemType type : model.getItemTypeRegistry().getAllItemTypes()) {
      if (type.supportsRepository()) {
        types.add(type);
      }
    }
    return types.toArray(new IItemType[types.size()]);
  }
}