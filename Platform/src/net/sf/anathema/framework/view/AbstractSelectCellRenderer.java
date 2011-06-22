package net.sf.anathema.framework.view;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSelectCellRenderer<T> extends DefaultListCellRenderer {

  private static final long serialVersionUID = 512344739558486209L;
  private final IResources resources;

  public AbstractSelectCellRenderer(IResources resources) {
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
    return resources.getString("ComboBox.SelectLabel"); //$NON-NLS-1$
  }

  protected abstract String getCustomizedDisplayValue(T value);

  protected final IResources getResources() {
    return resources;
  }
}