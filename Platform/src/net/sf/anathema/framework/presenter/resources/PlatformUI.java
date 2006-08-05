package net.sf.anathema.framework.presenter.resources;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class PlatformUI extends AbstractUI {

  public PlatformUI(IResources resources) {
    super(resources);
  }

  public Icon getPDFTaskBarIcon() {
    return getIcon("tools/TaskBarPDF24.png"); //$NON-NLS-1$
  }

  public Icon getSaveTaskBarIcon() {
    return getIcon("tools/TaskBarSave24.png"); //$NON-NLS-1$
  }

  public Icon getSaveAllTaskBarIcon() {
    return getIcon("tools/TaskBarSaveAll24.png"); //$NON-NLS-1$
  }

  public Icon getNewToolBarIcon() {
    return getIcon("tools/TaskBarNewArrow24.png"); //$NON-NLS-1$
  }

  public Icon getLoadToolBarIcon() {
    return getIcon("tools/TaskBarOpenArrow24.png"); //$NON-NLS-1$;
  }
}
