package net.sf.anathema.lib.gui.list;

import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

public abstract class LegalityCheckListCellRenderer extends DefaultListCellRenderer {
  private final Resources resources;

  protected LegalityCheckListCellRenderer(Resources resources) {
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

  protected String getPrintName(Resources res, Object value) {
    return res.getString(((Identifier) value).getId());
  }
}