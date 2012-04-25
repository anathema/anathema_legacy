package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;

public class SimpleItemTypeUi implements IObjectUi<Object> {

  private final Icon icon;

  public SimpleItemTypeUi(Icon icon) {
    this.icon = icon;
  }

  @Override
  public Icon getIcon(Object file) {
    return icon;
  }

  @Override
  public String getLabel(Object file) {
    return file.toString();
  }

  @Override
  public String getToolTipText(Object value) {
    return null;
  }
}