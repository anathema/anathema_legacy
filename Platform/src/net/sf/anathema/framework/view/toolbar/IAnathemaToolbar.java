package net.sf.anathema.framework.view.toolbar;

import javax.swing.Action;
import javax.swing.Icon;

public interface IAnathemaToolbar {

  void addTools(Action... action);

  void addMenu(Icon buttonIcon, Action[] menuActions, String toolTip);

  void addSeparator();
}