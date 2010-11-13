package net.sf.anathema.lib.gui.table.celleditors;

import java.util.EventObject;

import javax.swing.CellEditor;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

public class AbstractCellEditor implements CellEditor {
  protected EventListenerList listenerList = new EventListenerList();

  public Object getCellEditorValue() {
    return null;
  }

  public boolean isCellEditable(EventObject e) {
    return true;
  }

  public boolean shouldSelectCell(EventObject anEvent) {
    return false;
  }

  public boolean stopCellEditing() {
    fireEditingStopped();
    return true;
  }

  public void cancelCellEditing() {
    fireEditingCanceled();
  }

  public void addCellEditorListener(CellEditorListener l) {
    listenerList.add(CellEditorListener.class, l);
  }

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