package net.sf.anathema.lib.gui.list;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;

public class SmartJList extends JList {

  public SmartJList() {
    setModel(new DefaultListModel());
    setSelectionModel(new DefaultListSelectionModel());
  }

  public void setObjects(Object[] objects) {
    DefaultListModel listModel = (DefaultListModel) getModel();
    listModel.clear();
    for (Object object : objects) {
      listModel.addElement(object);
    }
  }

  public void setSelectedObjects(Object[] objects) {
    DefaultListModel model = (DefaultListModel) getModel();
    List<Integer> indexList = new ArrayList<Integer>();
    for (Object object : objects) {
      indexList.add(model.indexOf(object));
    }
    DefaultListSelectionModel selectionModel = (DefaultListSelectionModel) getSelectionModel();
    selectionModel.setValueIsAdjusting(true);
    selectionModel.clearSelection();
    for (Integer index : indexList) {
      selectionModel.addSelectionInterval(index, index);
    }
    selectionModel.setValueIsAdjusting(false);
  }
}