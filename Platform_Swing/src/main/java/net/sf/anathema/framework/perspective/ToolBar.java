package net.sf.anathema.framework.perspective;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;

public interface ToolBar {

  void addTools(Action... action);

  void addMenu(Icon buttonIcon, Action[] menuActions, String toolTip);

  void add(JComponent component);
}