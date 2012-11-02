package net.sf.anathema.lib.gui.list;

import net.sf.anathema.lib.lang.ArrayFactory;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

public class SmartJList<T> extends JList<T> {

  private Class<T> clazz;

  public SmartJList(Class<T> contentClass) {
    this.clazz = contentClass;
    setModel(new DefaultListModel<T>());
    setSelectionModel(new DefaultListSelectionModel());
    setSelectionMode(SINGLE_SELECTION);
  }

  public void setObjects(T[] objects) {
    DefaultListModel<T> listModel = (DefaultListModel) getModel();
    listModel.clear();
    for (T object : objects) {
      listModel.addElement(object);
    }
  }

  public void setSelectedObjects(T... objects) {
    DefaultListModel model = (DefaultListModel) getModel();
    List<Integer> indexList = new ArrayList<>();
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

  @Override
  public T[] getSelectedValues() {
    ArrayFactory<T> factory = new ArrayFactory<>(clazz);
    List<T> selectedValuesList = getSelectedValuesList();
    T[] array = factory.createArray(selectedValuesList.size());
    return selectedValuesList.toArray(array);
  }
}