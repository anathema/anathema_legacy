package net.sf.anathema.framework.presenter.resources;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class PlatformUI extends AbstractUI {

  public PlatformUI(IResources resources) {
    super(resources);
  }

  public Icon getPDFTaskBarIcon() {
    return getIcon("TaskBarPDF24.png"); //$NON-NLS-1$
  }

  public Icon getSaveTaskBarIcon() {
    return getIcon("TaskBarSave24.png"); //$NON-NLS-1$
  }

  public Icon getSaveIcon() {
    return getIcon("ButtonSave16.png"); //$NON-NLS-1$
  }

  public Icon getNewIcon() {
    return getIcon("ButtonNew16.png"); //$NON-NLS-1$
  }

  public Icon getSaveAllTaskBarIcon() {
    return getIcon("TaskBarSaveAll24.png"); //$NON-NLS-1$
  }

  public Icon getNewToolBarIcon() {
    return getIcon("TaskBarNewArrow24.png"); //$NON-NLS-1$
  }

  public Icon getLoadToolBarIcon() {
    return getIcon("TaskBarOpenArrow24.png"); //$NON-NLS-1$;
  }
}