package net.sf.anathema.lib.gui.list;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ComponentEnablingListSelectionListener implements ListSelectionListener {

  private final ListSelectionModel model;
  private final Component component;

  public ComponentEnablingListSelectionListener(Component component, ListSelectionModel model) {
    this.component = component;
    this.model = model;
  }

  public ComponentEnablingListSelectionListener(Component component, JList list) {
    this(component, list.getSelectionModel());
  }

  public void valueChanged(ListSelectionEvent e) {
    component.setEnabled(!model.isSelectionEmpty());
  }
}