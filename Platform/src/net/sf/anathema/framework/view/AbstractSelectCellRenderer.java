package net.sf.anathema.framework.view;

import net.sf.anathema.lib.resources.Resources;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

public abstract class AbstractSelectCellRenderer<T> extends DefaultListCellRenderer {

  private final Resources resources;

  public AbstractSelectCellRenderer(Resources resources) {
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    if (value == null) {
      return super.getListCellRendererComponent(list, getNullValue(), index, isSelected, cellHasFocus);
    }
    return super.getListCellRendererComponent(
        list,
        getCustomizedDisplayValue((T) value),
        index,
        isSelected,
        cellHasFocus);
  }

  protected String getNullValue() {
    return resources.getString("ComboBox.SelectLabel");
  }

  protected abstract String getCustomizedDisplayValue(T value);

  protected final Resources getResources() {
    return resources;
  }
}