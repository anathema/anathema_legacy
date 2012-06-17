package net.sf.anathema.framework.presenter.itemmanagement;

import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class SelectedItemCloseAction extends AbstractCloseAction {

  public static Action createMenuAction(IItemManagementModel model, IResources resources) {
    SmartAction action = new SelectedItemCloseAction(model, resources);
    action.setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    action.setName(resources.getString("AnathemaCore.Tools.Close.Name")); //$NON-NLS-1$
    return action;
  }

  private SelectedItemCloseAction(IItemManagementModel management, IResources resources) {
    super(management, resources);
  }

  @Override
  protected IItem getItemToClose() {
    return getManagement().getSelectedItem();
  }
}
