package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheck;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;
import java.awt.Component;

public class LegalityCheckListCellRenderer extends DefaultListCellRenderer {
  private final LegalityCheck check;
  private final AgnosticUIConfiguration configuration;

  public LegalityCheckListCellRenderer(LegalityCheck check, AgnosticUIConfiguration configuration) {
    this.check = check;
    this.configuration = configuration;
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    String printName = configuration.getLabel(value);
    boolean isLegal = check.isLegal(value);
    boolean hasFocus = cellHasFocus && isLegal;
    boolean selected = isSelected && isLegal;
    JComponent displayComponent = (JComponent) super.getListCellRendererComponent(list, printName, index, selected, hasFocus);
    String tooltip = configuration.getToolTipText(value);
    displayComponent.setToolTipText(tooltip);
    displayComponent.setEnabled(isLegal);
    return displayComponent;
  }
}