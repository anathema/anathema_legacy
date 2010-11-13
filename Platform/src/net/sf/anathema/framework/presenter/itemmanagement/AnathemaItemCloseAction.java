package net.sf.anathema.framework.presenter.itemmanagement;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaItemCloseAction extends AbstractAnathemaCloseAction {

  private final IItem item;

  public static Action createForItem(IItemMangementModel model, IResources resources, IItem item) {
    SmartAction action = new AnathemaItemCloseAction(model, item, resources);
    action.setToolTipText(resources.getString("AnathemaCore.Tools.Close.Tooltip")); //$NON-NLS-1$
    action.setIcon(new BasicUi(resources).getClearIcon());
    return action;
  }

  private AnathemaItemCloseAction(IItemMangementModel management, IItem item, IResources resources) {
    super(management, resources);
    this.item = item;
  }

  @Override
  protected IItem getItemToClose() {
    return item;
  }
}