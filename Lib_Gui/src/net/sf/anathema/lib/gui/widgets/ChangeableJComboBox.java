package net.sf.anathema.lib.gui.widgets;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

import net.sf.anathema.lib.UnselectingComboBoxModel;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;

public class ChangeableJComboBox<V> implements IChangeableJComboBox<V> {

  private final JComboBox comboBox;
  private final ObjectValueControl<V> control = new ObjectValueControl<V>();

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
    comboBox.addItemListener(new ItemListener() {
      @SuppressWarnings("unchecked")
      public void itemStateChanged(ItemEvent e) {
        control.fireValueChangedEvent((V) e.getItem());
      }
    });
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
    setSelectedObject(selectedItem);
    if (comboBox.getSelectedItem() == null) {
      control.fireValueChangedEvent(null);
    }
  }

  public void addObjectSelectionChangedListener(final IObjectValueChangedListener<V> listener) {
    control.addObjectValueChangeListener(listener);
  }

  public void removeObjectSelectionChangeListener(IObjectValueChangedListener<V> listener) {
    control.addObjectValueChangeListener(listener);
  }

  @SuppressWarnings("unchecked")
  public V getSelectedObject() {
    return (V) comboBox.getSelectedItem();
  }

  public void setRenderer(ListCellRenderer renderer) {
    comboBox.setRenderer(renderer);
  }

  public Dimension getPreferredSize() {
    return comboBox.getPreferredSize();
  }

  public void setPreferredSize(Dimension preferredSize) {
    comboBox.setPreferredSize(preferredSize);
  }
}