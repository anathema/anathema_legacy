//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.columsettings;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

// NOT_PUBLISHED
public class CheckBoxTableColumnSettings extends AbstractTableColumnSettings {

  private static final Border BORDER = new EmptyBorder(0, 3, 0, 0);

  public CheckBoxTableColumnSettings() {
    super(2);
  }

  public TableCellEditor getEditor() {
    return new DefaultCellEditor(new JCheckBox());
  }

  @Override
  public TableCellRenderer getRenderer() {
    return new TableCellRenderer() {
      public Component getTableCellRendererComponent(
          JTable table,
          Object value,
          boolean isSelected,
          final boolean hasFocus,
          int row,
          int column) {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(((Boolean) value).booleanValue());
        if (hasFocus && table.isCellEditable(row, column)) {
          checkBox.setForeground(UIManager.getColor("Table.focusCellForeground")); //$NON-NLS-1$
          checkBox.setBackground(UIManager.getColor("Table.focusCellBackground")); //$NON-NLS-1$
        }
        else if (isSelected) {
          checkBox.setForeground(table.getSelectionForeground());
          checkBox.setBackground(table.getSelectionBackground());
        }
        else {
          checkBox.setForeground(table.getForeground());
          checkBox.setBackground(table.getBackground());
        }
        checkBox.setBorder(BORDER);
        return checkBox;
      }
    };
  }
}