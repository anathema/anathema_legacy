package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

public class IdentificateListCellRenderer extends DefaultListCellRenderer {

  private final Resources resources;

  public IdentificateListCellRenderer(Resources resources) {
    this.resources = resources;
  }

  @Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    if (value instanceof Identified) {
      String printName = resources.getString(((Identified) value).getId());
      return super.getListCellRendererComponent(list, printName, index, isSelected, cellHasFocus);
    }
    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
  }
}