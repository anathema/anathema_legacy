package net.sf.anathema.framework.presenter.itemmanagement;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.SelectedItemActionEnabler;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaCloseAction extends SmartAction {

  private final IItemMangementModel management;
  private final IItem item;

  public static Action createForItem(IItemMangementModel model, IResources resources, IItem item) {
    SmartAction action = new AnathemaCloseAction(model, item);
    action.setToolTipText(resources.getString("AnathemaCore.Tools.Close.Tooltip")); //$NON-NLS-1$
    action.setIcon(new BasicUi(resources).getMediumClearIcon());
    return action;
  }

  public static Action createMenuAction(IItemMangementModel model, IResources resources) {
    SmartAction action = new AnathemaCloseAction(model);
    action.setName(resources.getString("AnathemaCore.Tools.Close.Name")); //$NON-NLS-1$
    return action;
  }

  private AnathemaCloseAction(IItemMangementModel management) {
    this(management, null);
  }

  private AnathemaCloseAction(IItemMangementModel management, IItem item) {
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_W, Event.CTRL_MASK));
    this.management = management;
    this.item = item;
    management.addListener(new SelectedItemActionEnabler(this, management.getSelectedItem()));
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    if (item == null) {
      management.removeItem();
    }
    else {
      management.removeItem(item);
    }
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
}