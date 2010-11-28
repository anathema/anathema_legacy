package net.sf.anathema.framework.presenter.view;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class IdentificateListCellRenderer extends DefaultListCellRenderer {

  private final IResources resources;

  public IdentificateListCellRenderer(IResources resources) {
    this.resources = resources;
  }

  @Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    if (value instanceof IIdentificate) {
      String printName = resources.getString(((IIdentificate) value).getId());
      return super.getListCellRendererComponent(list, printName, index, isSelected, cellHasFocus);
    }
    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
  }
}