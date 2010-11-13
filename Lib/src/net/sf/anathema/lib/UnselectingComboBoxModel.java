package net.sf.anathema.lib;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

import net.disy.commons.core.util.ObjectUtilities;

public class UnselectingComboBoxModel extends AbstractListModel implements MutableComboBoxModel, Serializable {
  private List<Object> objects;
  private Object selectedObject;

  public UnselectingComboBoxModel() {
    objects = new Vector<Object>();
  }

  public UnselectingComboBoxModel(final Object items[]) {
    this();
    if (items == null) {
      return;
    }
    for (Object item : items) {
      objects.add(item);
    }
  }

  public void setSelectedItem(Object anObject) {
    if (!ObjectUtilities.equals(selectedObject, anObject)) {
      selectedObject = anObject;
      fireContentsChanged(this, -1, -1);
    }
  }

  public Object getSelectedItem() {
    return selectedObject;
  }

  public int getSize() {
    return objects.size();
  }

  public Object getElementAt(int index) {
    if (index >= 0 && index < objects.size()) {
      return objects.get(index);
    }
    return null;
  }

  public int getIndexOf(Object anObject) {
    return objects.indexOf(anObject);
  }

  public void addElement(Object anObject) {
    objects.add(anObject);
    fireIntervalAdded(this, objects.size() - 1, objects.size() - 1);
  }

  public void insertElementAt(Object anObject, int index) {
    objects.add(index, anObject);
    fireIntervalAdded(this, index, index);
  }

  public void removeElementAt(int index) {
    if (getElementAt(index) == selectedObject) {
      selectedObject = null;
    }
    objects.remove(index);
    fireIntervalRemoved(this, index, index);
  }

  public void removeElement(Object anObject) {
    int index = objects.indexOf(anObject);
    if (index != -1) {
      removeElementAt(index);
    }
  }

  public void removeAllElements() {
    selectedObject = null;
    if (objects.size() > 0) {
      int firstIndex = 0;
      int lastIndex = objects.size() - 1;
      objects.clear();
      fireIntervalRemoved(this, firstIndex, lastIndex);
    }
  }
}