//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.celleditors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.EventObject;

// NOT_PUBLISHED
public class EditorDelegate implements ActionListener, ItemListener, Serializable {

  private final AbstractDelegatingCellEditor editor;

  public EditorDelegate(AbstractDelegatingCellEditor editor) {
    this.editor = editor;
  }

  private static final int CLICK_COUNT_TO_START = 2;
  private Object value;

  public Object getCellEditorValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public boolean isCellEditable(EventObject anEvent) {
    if (anEvent instanceof MouseEvent) {
      return ((MouseEvent) anEvent).getClickCount() >= CLICK_COUNT_TO_START;
    }
    return true;
  }

  public boolean stopCellEditing() {
    editor.fireEditingStopped();
    return true;
  }

  public void cancelCellEditing() {
    editor.fireEditingCanceled();
  }

  public void actionPerformed(ActionEvent e) {
    this.editor.stopCellEditing();
  }

  public void itemStateChanged(ItemEvent e) {
    this.editor.stopCellEditing();
  }
}