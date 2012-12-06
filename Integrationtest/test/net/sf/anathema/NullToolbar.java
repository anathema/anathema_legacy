package net.sf.anathema;

import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

import javax.swing.Action;
import javax.swing.Icon;

public class NullToolbar implements IAnathemaToolbar {
  @Override
  public void addTools(Action... action) {
    //nothing to do
  }

  @Override
  public void addMenu(Icon buttonIcon, Action[] menuActions, String toolTip) {
    //nothing to do
  }

  @Override
  public void addSeparator() {
    //nothing to do
  }
}