//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.celleditors;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.EventObject;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;

// NOT_PUBLISHED
public abstract class AbstractDelegatingCellEditor extends AbstractCellEditor implements
    TableCellEditor,
    TreeCellEditor {

  private final JComponent editorComponent;
  private final EditorDelegate delegate;

  public AbstractDelegatingCellEditor() {
    editorComponent = createEditorComponent();
    delegate = createDelegate(editorComponent);
    try {
      Method addActionListener = editorComponent.getClass().getMethod(
          "addActionListener", new Class[] { ActionListener.class }); //$NON-NLS-1$
      addActionListener.invoke(editorComponent, new Object[] { delegate });
    }
    catch (Exception exception) {
      // ignore problems - we don't know wether an action listener can be registered at all
    }
  }

  @Override
  public final Object getCellEditorValue() {
    return delegate.getCellEditorValue();
  }

  @Override
  public final boolean isCellEditable(EventObject anEvent) {
    return delegate.isCellEditable(anEvent);
  }

  @Override
  public final boolean shouldSelectCell(EventObject anEvent) {
    return true;
  }

  @Override
  public final boolean stopCellEditing() {
    return delegate.stopCellEditing();
  }

  @Override
  public final void cancelCellEditing() {
    delegate.cancelCellEditing();
  }

  public final Component getTreeCellEditorComponent(
      JTree tree,
      Object value,
      boolean isSelected,
      boolean expanded,
      boolean leaf,
      int row) {
    delegate.setValue(value);
    return editorComponent;
  }

  public final Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    delegate.setValue(value);
    return editorComponent;
  }

  protected JComponent getEditorComponent() {
    return editorComponent;
  }

  protected abstract EditorDelegate createDelegate(JComponent editor);

  protected abstract JComponent createEditorComponent();
}