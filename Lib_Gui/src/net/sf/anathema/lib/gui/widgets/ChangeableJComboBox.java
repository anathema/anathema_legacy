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

public class ChangeableJComboBox<V> implements IChangeableJComboBox<V> {

  private JComboBox comboBox;
  private final Map<IObjectValueChangedListener<V>, ItemListener> listenersByListener = new HashMap<IObjectValueChangedListener<V>, ItemListener>();

  public ChangeableJComboBox(boolean editable) {
    this(new UnselectingComboBoxModel(), editable);
  }

  public ChangeableJComboBox(V[] objects, boolean editable) {
    this(new UnselectingComboBoxModel(objects), editable);
  }

  private ChangeableJComboBox(UnselectingComboBoxModel model, boolean editable) {
    this.comboBox = new ColoredJComboBox(model);
    this.comboBox.setEditable(editable);
    setSelectedObject(null);
  }

  public JComboBox getComponent() {
    return comboBox;
  }

  public void setSelectedObject(Object object) {
    comboBox.setSelectedItem(object);
  }

  public void setObjects(V[] objects) {
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

  public void addObjectSelectionChangedListener(final IObjectValueChangedListener<V> listener) {
    ItemListener itemListener = new ItemListener() {
      @SuppressWarnings("unchecked")
      public void itemStateChanged(ItemEvent e) {
        listener.valueChanged((V) e.getItem());
      }
    };
    comboBox.addItemListener(itemListener);
    listenersByListener.put(listener, itemListener);
  }

  @SuppressWarnings("unchecked")
  public V getSelectedObject() {
    return (V) comboBox.getSelectedItem();
  }

  public void setRenderer(ListCellRenderer renderer) {
    comboBox.setRenderer(renderer);
  }

  public void removeObjectSelectionChangeListener(IObjectValueChangedListener<V> listener) {
    comboBox.removeItemListener(listenersByListener.get(listener));
  }

  public Dimension getPreferredSize() {
    return comboBox.getPreferredSize();
  }

  public void setPreferredSize(Dimension preferredSize) {
    comboBox.setPreferredSize(preferredSize);
  }
}
