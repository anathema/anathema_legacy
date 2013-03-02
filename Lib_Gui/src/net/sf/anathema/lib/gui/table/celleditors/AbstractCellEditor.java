package net.sf.anathema.lib.gui.table.celleditors;

import javax.swing.CellEditor;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import java.util.EventObject;

public class AbstractCellEditor implements CellEditor {
  protected EventListenerList listenerList = new EventListenerList();

  @Override
  public Object getCellEditorValue() {
    return null;
  }

  @Override
  public boolean isCellEditable(EventObject e) {
    return true;
  }

  @Override
  public boolean shouldSelectCell(EventObject anEvent) {
    return false;
  }

  @Override
  public boolean stopCellEditing() {
    fireEditingStopped();
    return true;
  }

  @Override
  public void cancelCellEditing() {
    fireEditingCanceled();
  }

  @Override
  public void addCellEditorListener(CellEditorListener l) {
    listenerList.add(CellEditorListener.class, l);
  }

  @Override
  public void removeCellEditorListener(CellEditorListener l) {
    listenerList.remove(CellEditorListener.class, l);
  }

  protected void fireEditingStopped() {
    Object[] listeners = listenerList.getListenerList();
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == CellEditorListener.class) {
        ((CellEditorListener) listeners[i + 1]).editingStopped(new ChangeEvent(this));
      }
    }
  }

  protected void fireEditingCanceled() {
    Object[] listeners = listenerList.getListenerList();
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == CellEditorListener.class) {
        ((CellEditorListener) listeners[i + 1]).editingCanceled(new ChangeEvent(this));
      }
    }
  }
}