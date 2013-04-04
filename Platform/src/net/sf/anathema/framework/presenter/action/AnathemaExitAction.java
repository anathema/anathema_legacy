package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;
import java.awt.Component;

public class AnathemaExitAction extends SmartAction {

  @Override
  protected void execute(Component parentComponent) {
    System.exit(0);
  }

  public static Action createMenuAction(Resources resources) {
    SmartAction action = new AnathemaExitAction();
    action.setName(resources.getString("AnathemaCore.Tools.Exit.Name")); //$NON-NLS-1$
    return action;
  }
}