package net.sf.anathema.framework.view.toolbar;

import javax.swing.Action;
import javax.swing.Icon;

public interface IAnathemaToolbar {

  public void addTools(Action... action);

  public void addMenu(Icon buttonIcon, Action... menuActions);
}