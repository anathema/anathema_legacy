package net.sf.anathema.lib;

import com.google.common.base.Objects;

import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class UnselectingComboBoxModel extends AbstractListModel implements MutableComboBoxModel, Serializable {
  private final List<Object> objects = new Vector<>();
  private Object selectedObject;

  @Override
  public void setSelectedItem(Object anObject) {
    if (!Objects.equal(selectedObject, anObject)) {
      selectedObject = anObject;
      fireContentsChanged(this, -1, -1);
    }
  }

  @Override
  public Object getSelectedItem() {
    return selectedObject;
  }

  @Override
  public int getSize() {
    return objects.size();
  }

  @Override
  public Object getElementAt(int index) {
    if (index >= 0 && index < objects.size()) {
      return objects.get(index);
    }
    return null;
  }

  @Override
  public void addElement(Object anObject) {
    objects.add(anObject);
    fireIntervalAdded(this, objects.size() - 1, objects.size() - 1);
  }

  @Override
  public void insertElementAt(Object anObject, int index) {
    objects.add(index, anObject);
    fireIntervalAdded(this, index, index);
  }

  @Override
  public void removeElementAt(int index) {
    if (getElementAt(index) == selectedObject) {
      selectedObject = null;
    }
    objects.remove(index);
    fireIntervalRemoved(this, index, index);
  }

  @Override
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