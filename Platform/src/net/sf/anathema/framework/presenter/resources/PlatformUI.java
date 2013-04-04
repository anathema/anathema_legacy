package net.sf.anathema.framework.presenter.resources;

import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class PlatformUI extends AbstractUI {

  public Icon getPDFTaskBarIcon() {
    return getIcon("icons/TaskBarPDF24.png"); //$NON-NLS-1$
  }

  public Icon getSaveTaskBarIcon() {
    return getIcon("icons/TaskBarSave24.png"); //$NON-NLS-1$
  }

  public Icon getSaveAllTaskBarIcon() {
    return getIcon("icons/TaskBarSaveAll24.png"); //$NON-NLS-1$
  }

  public Icon getNewToolBarIcon() {
    return getIcon("icons/TaskBarNewArrow24.png"); //$NON-NLS-1$
  }

  public Icon getLoadToolBarIcon() {
    return getIcon("icons/TaskBarOpenArrow24.png"); //$NON-NLS-1$;
  }
}