package net.sf.anathema.framework.presenter.itemmanagement;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaCloseAction extends AbstractAnathemaCloseAction {

  private static final long serialVersionUID = 6219742679646432771L;

  public static Action createMenuAction(IItemManagementModel model, IResources resources) {
    SmartAction action = new AnathemaCloseAction(model, resources);
    action.setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    action.setName(resources.getString("AnathemaCore.Tools.Close.Name")); //$NON-NLS-1$
    return action;
  }

  private AnathemaCloseAction(IItemManagementModel management, IResources resources) {
    super(management, resources);
  }

  @Override
  protected IItem getItemToClose() {
    return getManagement().getSelectedItem();
  }
}
