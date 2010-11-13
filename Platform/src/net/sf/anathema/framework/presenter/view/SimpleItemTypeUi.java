package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;

public class SimpleItemTypeUi implements IObjectUi {

  private final Icon icon;

  public SimpleItemTypeUi(Icon icon) {
    this.icon = icon;
  }

  public Icon getIcon(Object file) {
    return icon;
  }

  public String getLabel(Object file) {
    return file.toString();
  }
}