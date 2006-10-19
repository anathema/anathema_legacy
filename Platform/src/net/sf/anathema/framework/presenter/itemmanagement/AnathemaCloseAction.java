package net.sf.anathema.framework.presenter.itemmanagement;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.SelectedItemActionEnabler;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaCloseAction extends SmartAction {

  private final IItemMangementModel management;

  public static Action createMenuAction(IItemMangementModel model, IResources resources) {
    SmartAction action = new AnathemaCloseAction(model);
    action.setName(resources.getString("AnathemaCore.Tools.Close.Name")); //$NON-NLS-1$
    return action;
  }

  private AnathemaCloseAction(IItemMangementModel management) {
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    this.management = management;
    management.addListener(new SelectedItemActionEnabler(this, management.getSelectedItem()));
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    management.removeItem();
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
}