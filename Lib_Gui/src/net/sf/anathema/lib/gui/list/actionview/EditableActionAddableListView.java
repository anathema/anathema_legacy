package net.sf.anathema.lib.gui.list.actionview;

import net.sf.anathema.lib.gui.table.SmartTable;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;
import net.sf.anathema.lib.lang.ArrayFactory;

import javax.swing.JComponent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class EditableActionAddableListView<V> extends AbstractActionAddableListView<V> {

  private final SmartTable table;
  private final DefaultTableModel tableModel = new DefaultTableModel(10, 1);
  private final ArrayFactory<V> factory;

  public EditableActionAddableListView(ITableColumnViewSettings columnSetting, Class<V> contentClass) {
    this.table = new SmartTable(tableModel, new ITableColumnViewSettings[] { columnSetting });
    table.getTable().setTableHeader(null);
    table.getTable().setGridColor(new Color(0, 0, 0, 0));
    this.factory = new ArrayFactory<>(contentClass);
  }

  @Override
  protected JComponent getDisplayComponent() {
    return table.getComponent();
  }

  @Override
  public void setObjects(V[] items) {
    for (int index = 0; index < tableModel.getRowCount(); index++) {
      tableModel.removeRow(index);
    }
    tableModel.setRowCount(0);
    for (V value : items) {
      tableModel.addRow(new Object[] { value });
    }
  }

  @Override
  public void addListSelectionListener(final Runnable listener) {
    table.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        listener.run();
      }
    });
  }

  @Override
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