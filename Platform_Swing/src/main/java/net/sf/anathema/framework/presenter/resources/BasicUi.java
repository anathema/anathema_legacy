package net.sf.anathema.framework.presenter.resources;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class BasicUi extends AbstractUI {

  public RelativePath getNewIconPathForTaskbar () {
    return new RelativePath("icons/TaskBarNew24.png");
  }

  public Icon getRemoveIcon() {
    return getIcon(getRemoveIconPath());
  }

  public Icon getAddIcon() {
    return getIcon(getAddIconPath());
  }

  public Icon getClearIcon() {
    return getIcon(new RelativePath("icons/ButtonCross16.png"));
  }

  @SuppressWarnings("UnusedDeclaration")
  public Icon getLeftArrowIcon() {
    return getIcon(new RelativePath("icons/ButtonArrowLeft16.png"));
  }

  public Icon getRightArrowIcon() {
    return getIcon(getRightArrowIconPath());
  }

  public RelativePath getRightArrowIconPath() {
    return new RelativePath("icons/ButtonArrowRight16.png");
  }

  public Icon getUpArrowIcon() {
    return getIcon(new RelativePath("icons/ButtonArrowUp16.png"));
  }

  public Icon getDownArrowIcon() {
    return getIcon(new RelativePath("icons/ButtonArrowDown16.png"));
  }

  public Icon getEditIcon() {
    return getIcon(getEditIconPath());
  }

  public RelativePath getEditIconPath() {
    return new RelativePath("icons/ButtonEdit16.png");
  }

  public RelativePath getRemoveIconPath() {
    return new RelativePath("icons/ButtonMinus16.png");
  }

  public RelativePath getAddIconPath() {
    return new RelativePath("icons/ButtonPlus16.png");
  }
}