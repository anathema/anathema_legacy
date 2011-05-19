package net.sf.anathema.lib.gui.list;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;

import net.disy.commons.core.util.ArrayUtilities;

public class SmartJList<T> extends JList {

  private static final long serialVersionUID = 9175647149496650572L;
private Class< ? extends T> clazz;

  public SmartJList(Class< ? extends T> contentClass) {
    this.clazz = contentClass;
    setModel(new DefaultListModel());
    setSelectionModel(new DefaultListSelectionModel());
  }

  public void setObjects(T[] objects) {
    DefaultListModel listModel = (DefaultListModel) getModel();
    listModel.clear();
    for (Object object : objects) {
      listModel.addElement(object);
    }
  }

  public void setSelectedObjects(T... objects) {
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

  @SuppressWarnings("unchecked")
  @Override
  public T getSelectedValue() {
    return (T) super.getSelectedValue();
  }

  @Override
  public T[] getSelectedValues() {
    return ArrayUtilities.transform(super.getSelectedValues(), clazz);
  }

  public void setSelectionMode(ListSelectionMode mode) {
    setSelectionMode(mode.getMode());
  }
}