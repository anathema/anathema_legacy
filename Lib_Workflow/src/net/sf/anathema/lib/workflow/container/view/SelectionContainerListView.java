package net.sf.anathema.lib.workflow.container.view;

import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.list.SmartJList;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;

public class SelectionContainerListView implements ISelectionContainerView {

  private final SmartJList smartList = new SmartJList();
  private final ChangeControl changeControl = new ChangeControl();

  public SelectionContainerListView() {
    smartList.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
          return;
        }
        changeControl.fireChangedEvent();
      }
    });
  }

  public void populate(Object[] contentValues) {
    smartList.setObjects(contentValues);
  }

  public void setSelectedValues(Object[] selectedValues) {
    smartList.setSelectedObjects(selectedValues);
  }

  public JList getContent() {
    return smartList;
  }

  public void addSelectionChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  public Object[] getSelectedValues() {
    return smartList.getSelectedValues();
  }

  public void setRenderer(ListCellRenderer renderer) {
    smartList.setCellRenderer(renderer);
  }
}