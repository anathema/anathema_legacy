package net.sf.anathema.framework.view;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSelectCellRenderer extends DefaultListCellRenderer {

  private final IResources resources;

  public AbstractSelectCellRenderer(IResources resources) {
    this.resources = resources;
  }

  @Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    if (value == null) {
      Object selectString = getNullValue();
      return super.getListCellRendererComponent(list, selectString, index, isSelected, cellHasFocus);
    }
    return super.getListCellRendererComponent(list, getCustomizedDisplayValue(value), index, isSelected, cellHasFocus);
  }

  protected Object getNullValue() {
    return resources.getString("ComboBox.SelectLabel"); //$NON-NLS-1$
  }

  protected abstract Object getCustomizedDisplayValue(Object value);

  protected final IResources getResources() {
    return resources;
  }
}