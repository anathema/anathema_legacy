package net.sf.anathema.lib.gui.list.actionview;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.sf.anathema.lib.gui.table.SmartTable;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

public class EditableActionAddableListView extends AbstractActionAddableListView {

  private final SmartTable table;
  private final DefaultTableModel tableModel = new DefaultTableModel(10, 1);

  public EditableActionAddableListView(String title, ITableColumnViewSettings columnSetting) {
    super(title);
    table = new SmartTable(tableModel, new ITableColumnViewSettings[] { columnSetting });
    table.getTable().setTableHeader(null);
    table.getTable().setGridColor(new Color(0, 0, 0, 0));
  }

  @Override
  protected JComponent getDisplayComponent() {
    return table.getContent();
  }

  public void setListItems(Object[] items) {
    for (int index = 0; index < tableModel.getRowCount(); index++) {
      tableModel.removeRow(index);
    }
    tableModel.setRowCount(0);
    for (Object value : items) {
      tableModel.addRow(new Object[] { value });
    }
  }

  public void addListSelectionListener(ListSelectionListener listener) {
    table.getTable().getSelectionModel().addListSelectionListener(listener);
  }

  public Object[] getSelectedItems() {
    int selectedRowIndex = table.getSelectedRowIndex();
    if (selectedRowIndex < 0) {
      return new Object[0];
    }
    return new Object[] { tableModel.getValueAt(selectedRowIndex, 0) };
  }

  @Override
  protected boolean isScrollable() {
    return false;
  }
}