package net.sf.anathema.lib.gui.list;

import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;
import java.awt.Component;

public abstract class LegalityCheckListCellRenderer extends DefaultListCellRenderer {
  private final LegalityCheck check;

  protected LegalityCheckListCellRenderer(LegalityCheck check) {
    this.check = check;
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    String printName = getPrintName(value);
    boolean isLegal = check.isLegal(value);
    boolean hasFocus = cellHasFocus && isLegal;
    boolean selected = isSelected && isLegal;
    JComponent displayComponent = (JComponent) super.getListCellRendererComponent(list, printName, index, selected, hasFocus);
    String tooltip = getToolTip(value);
    displayComponent.setToolTipText(tooltip);
    displayComponent.setEnabled(isLegal);
    return displayComponent;
  }

  protected abstract String getPrintName(Object value);

  protected abstract String getToolTip(Object value);
}