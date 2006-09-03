package net.sf.anathema.lib.gui.list.actionview;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.sf.anathema.lib.gui.table.SmartTable;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;
import net.sf.anathema.lib.lang.ArrayFactory;

public class EditableActionAddableListView<V> extends AbstractActionAddableListView<V> {

  private final SmartTable table;
  private final DefaultTableModel tableModel = new DefaultTableModel(10, 1);
  private final ArrayFactory<V> factory;

  public EditableActionAddableListView(String title, ITableColumnViewSettings columnSetting, Class<V> contentClass) {
    super(title);
    table = new SmartTable(tableModel, new ITableColumnViewSettings[] { columnSetting });
    table.getTable().setTableHeader(null);
    table.getTable().setGridColor(new Color(0, 0, 0, 0));
    factory = new ArrayFactory<V>(contentClass);
  }

  @Override
  protected JComponent getDisplayComponent() {
    return table.getContent();
  }

  public void setObjects(V[] items) {
    for (int index = 0; index < tableModel.getRowCount(); index++) {
      tableModel.removeRow(index);
    }
    tableModel.setRowCount(0);
    for (V value : items) {
      tableModel.addRow(new Object[] { value });
    }
  }

  public void addListSelectionListener(ListSelectionListener listener) {
    table.getTable().getSelectionModel().addListSelectionListener(listener);
  }

  @SuppressWarnings("unchecked")
  public V[] getSelectedItems() {
    int selectedRowIndex = table.getSelectedRowIndex();
    if (selectedRowIndex < 0) {
      return factory.createArray(0);
    }
    V[] array = factory.createArray(1);
    array[0] = (V) tableModel.getValueAt(selectedRowIndex, 0);
    return array;
  }

  @Override
  protected boolean isScrollable() {
    return false;
  }
}