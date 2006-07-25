package net.sf.anathema.framework.presenter.resources;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class BasicUi extends AbstractUI {

  public BasicUi(IResources resources) {
    super(resources);
  }

  public Icon getMediumRemoveIcon() {
    return getIcon("ButtonMinus16.png"); //$NON-NLS-1$
  }

  public Icon getSmallRemoveIcon() {
    return getIcon("tools/ButtonCross16.png"); //$NON-NLS-1$
  }

  public Icon getMediumAddIcon() {
    return getIcon("ButtonPlus16.png"); //$NON-NLS-1$
  }

  public Icon getSmallAddIcon() {
    return getIcon("ButtonPlus16.png"); //$NON-NLS-1$
  }

  public Icon getDoubleLeftArrowIcon() {
    return getIcon("ButtonArrowLeft16.png"); //$NON-NLS-1$
  }

  public Icon getDoubleRightArrowIcon() {
    return getIcon("ButtonArrowRight16.png"); //$NON-NLS-1$
  }

  public Icon getSingleUpArrowIcon() {
    return getIcon("ButtonArrowUp16.png"); //$NON-NLS-1$
  }

  public Icon getSingleDownArrowIcon() {
    return getIcon("ButtonArrowDown16.png"); //$NON-NLS-1$
  }

  public Icon getDoubleUpArrowIcon() {
    return getIcon("ButtonArrowUp16.png"); //$NON-NLS-1$
  }

  public Icon getDoubleDownArrowIcon() {
    return getIcon("ButtonArrowDown16.png"); //$NON-NLS-1$
  }

  public Icon getMediumClearIcon() {
    return getIcon("tools/ButtonCross16.png"); //$NON-NLS-1$
  }
}