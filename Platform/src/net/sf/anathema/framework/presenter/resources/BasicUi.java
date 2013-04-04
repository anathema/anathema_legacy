package net.sf.anathema.framework.presenter.resources;

import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class BasicUi extends AbstractUI {

  public Icon getRemoveIcon() {
    return getIcon("icons/ButtonMinus16.png"); //$NON-NLS-1$
  }

  public Icon getAddIcon() {
    return getIcon("icons/ButtonPlus16.png"); //$NON-NLS-1$
  }

  public Icon getClearIcon() {
    return getIcon("icons/ButtonCross16.png"); //$NON-NLS-1$
  }

  @SuppressWarnings("UnusedDeclaration")
  public Icon getLeftArrowIcon() {
    return getIcon("icons/ButtonArrowLeft16.png"); //$NON-NLS-1$
  }

  public Icon getRightArrowIcon() {
    return getIcon("icons/ButtonArrowRight16.png"); //$NON-NLS-1$
  }

  public Icon getUpArrowIcon() {
    return getIcon("icons/ButtonArrowUp16.png"); //$NON-NLS-1$
  }

  public Icon getDownArrowIcon() {
    return getIcon("icons/ButtonArrowDown16.png"); //$NON-NLS-1$
  }

  public Icon getEditIcon() {
    return getIcon("icons/ButtonEdit16.png"); //$NON-NLS-1$
  }
}