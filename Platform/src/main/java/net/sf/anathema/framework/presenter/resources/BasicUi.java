package net.sf.anathema.framework.presenter.resources;

import net.sf.anathema.lib.file.RelativePath;

public class BasicUi {

  public RelativePath getNewIconPathForTaskbar () {
    return new RelativePath("icons/TaskBarNew24.png");
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

  public RelativePath getClearIconPath() {
    return new RelativePath("icons/ButtonCross16.png");
  }

  public RelativePath getRightArrowIconPath() {
    return new RelativePath("icons/ButtonArrowRight16.png");
  }

  public RelativePath getLeftArrowIconPath() {
    return new RelativePath("icons/ButtonArrowLeft16.png");
  }
}