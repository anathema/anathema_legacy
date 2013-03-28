package net.sf.anathema.lib.gui.ui;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.icon.DisabledIconDecorator;
import net.sf.anathema.lib.lang.StringUtilities;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import java.awt.Component;

public class ObjectUiListCellRenderer extends DefaultListCellRenderer {

  private final ObjectUi ui;

  public ObjectUiListCellRenderer(ObjectUi ui) {
    Preconditions.checkNotNull(ui);
    this.ui = ui;
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    Icon icon = ui.getIcon(value);
    if (icon != null && !list.isEnabled()) {
      icon = new DisabledIconDecorator(icon);
    }
    setIcon(icon);
    setDisabledIcon(icon == null ? null : new DisabledIconDecorator(icon));
    String label = ui.getLabel(value);
    setText(label == null || label.equals("") ? " " : label);
    String toolTipText = ui.getToolTipText(value);
    setToolTipText(StringUtilities.isNullOrEmpty(toolTipText) ? null : toolTipText);
    return this;
  }
}