package net.sf.anathema.framework.presenter.resources;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class PlatformUI extends AbstractUI {

  public Icon getPDFTaskBarIcon() {
    return getIcon(new RelativePath("icons/TaskBarPDF24.png"));
  }

  public Icon getSaveTaskBarIcon() {
    return getIcon(new RelativePath("icons/TaskBarSave24.png"));
  }

  public Icon getSaveAllTaskBarIcon() {
    return getIcon(new RelativePath("icons/TaskBarSaveAll24.png"));
  }

  public Icon getNewToolBarIcon() {
    return getIcon(new RelativePath("icons/TaskBarNewArrow24.png"));
  }

  public Icon getLoadToolBarIcon() {
    return getIcon(new RelativePath("icons/TaskBarOpenArrow24.png"));
  }
}