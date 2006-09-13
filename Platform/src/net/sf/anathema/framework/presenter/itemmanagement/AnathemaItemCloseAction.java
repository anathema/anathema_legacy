package net.sf.anathema.framework.presenter.itemmanagement;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.SelectedItemActionEnabler;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaItemCloseAction extends SmartAction {
  private final IItemMangementModel management;
  private final IItem item;

  public static Action createForItem(IItemMangementModel model, IResources resources, IItem item) {
    SmartAction action = new AnathemaItemCloseAction(model, item);
    action.setToolTipText(resources.getString("AnathemaCore.Tools.Close.Tooltip")); //$NON-NLS-1$
    action.setIcon(new BasicUi(resources).getClearIcon());
    return action;
  }

  private AnathemaItemCloseAction(IItemMangementModel management, IItem item) {
    this.management = management;
    this.item = item;
    management.addListener(new SelectedItemActionEnabler(this, management.getSelectedItem()));
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    management.removeItem(item);
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
}