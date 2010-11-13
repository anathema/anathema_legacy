//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

// NOT_PUBLISHED
public class TableColumnConfigurator {

  public static void configureTableColumns(JTable table, ITableColumnViewSettings[] settings) {
    TableColumnModel columnModel = table.getColumnModel();
    for (int columnIndex = 0; columnIndex < settings.length; columnIndex++) {
      TableColumn tableColumn = columnModel.getColumn(columnIndex);
      ITableColumnViewSettings view = settings[columnIndex];
      tableColumn.setCellEditor(view.getEditor());
      if (view.getRenderer() != null) {
        tableColumn.setCellRenderer(view.getRenderer());
      }
      tableColumn.setPreferredWidth(view.getPreferredWidth());
      tableColumn.setWidth(view.getPreferredWidth());
      if (!view.isResizable()) {
        tableColumn.setResizable(view.isResizable());
        tableColumn.setMinWidth(view.getPreferredWidth());
        tableColumn.setMaxWidth(view.getPreferredWidth());
      }
    }
  }
}