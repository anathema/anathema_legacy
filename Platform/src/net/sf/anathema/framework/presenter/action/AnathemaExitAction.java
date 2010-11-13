package net.sf.anathema.framework.presenter.action;

import java.awt.Component;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaExitAction extends SmartAction {

  @Override
  protected void execute(Component parentComponent) {
    System.exit(0);
  }

  public static Action createMenuAction(IResources resources) {
    SmartAction action = new AnathemaExitAction();
    action.setName(resources.getString("AnathemaCore.Tools.Exit.Name")); //$NON-NLS-1$
    return action;
  }
}