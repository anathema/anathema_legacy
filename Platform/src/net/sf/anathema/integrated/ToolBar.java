package net.sf.anathema.integrated;

import javax.swing.Action;
import javax.swing.Icon;

public interface ToolBar {

  void addTools(Action... action);

  void addMenu(Icon buttonIcon, Action[] menuActions, String toolTip);
}