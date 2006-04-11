package net.sf.anathema.lib.gui.widgets;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

import net.sf.anathema.lib.UnselectingComboBoxModel;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class ChangeableJComboBox<T> implements IChangeableJComboBox<T> {

  private JComboBox comboBox;
  private final Map<IObjectValueChangedListener, ItemListener> listenersByListener = new HashMap<IObjectValueChangedListener, ItemListener>();

  public ChangeableJComboBox(Object[] objects, boolean editable) {
    this.comboBox = new ColoredJComboBox(new UnselectingComboBoxModel(objects));
    this.comboBox.setEditable(editable);
    setSelectedObject(null);
  }

  public JComboBox getComponent() {
    return comboBox;
  }

  public void setSelectedObject(T object) {
    comboBox.setSelectedItem(object);
  }

  public void setObjects(T[] objects) {
    Object selectedItem = comboBox.getSelectedItem();
    UnselectingComboBoxModel model = (UnselectingComboBoxModel) comboBox.getModel();
    model.removeAllElements();
    for (Object object : objects) {
      model.addElement(object);
    }
    comboBox.setSelectedItem(selectedItem);
  }

  public void clearObjects() {
    UnselectingComboBoxModel model = (UnselectingComboBoxModel) comboBox.getModel();
    model.removeAllElements();
  }

  public void addObjectSelectionChangedListener(final IObjectValueChangedListener listener) {
    ItemListener itemListener = new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        listener.valueChanged(e.getItem());
      }
    };
    comboBox.addItemListener(itemListener);
    listenersByListener.put(listener, itemListener);
  }

  @SuppressWarnings("unchecked")
  public T getSelectedObject() {
    return (T) comboBox.getSelectedItem();
  }

  public void setRenderer(ListCellRenderer renderer) {
    comboBox.setRenderer(renderer);
  }

  public void removeObjectSelectionChangeListener(IObjectValueChangedListener listener) {
    comboBox.removeItemListener(listenersByListener.get(listener));
  }

  public Dimension getPreferredSize() {
    return comboBox.getPreferredSize();
  }

  public void setPreferredSize(Dimension preferredSize) {
    comboBox.setPreferredSize(preferredSize);
  }
}
