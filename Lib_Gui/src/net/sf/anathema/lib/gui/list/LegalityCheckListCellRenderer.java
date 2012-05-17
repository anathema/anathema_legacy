package net.sf.anathema.lib.gui.list;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

public abstract class LegalityCheckListCellRenderer extends DefaultListCellRenderer {
  private final IResources resources;

  protected LegalityCheckListCellRenderer(IResources resources) {
    this.resources = resources;
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    String printName = getPrintName(resources, value);
    boolean isLegal = isLegal(value);
    boolean hasFocus = cellHasFocus && isLegal;
    boolean selected = isSelected && isLegal;
    Component displayComponent = super.getListCellRendererComponent(list, printName, index, selected, hasFocus);
    displayComponent.setEnabled(isLegal);
    return displayComponent;
  }

  protected abstract boolean isLegal(Object object);

  protected String getPrintName(IResources res, Object value) {
    return res.getString(((IIdentificate) value).getId());
  }
}