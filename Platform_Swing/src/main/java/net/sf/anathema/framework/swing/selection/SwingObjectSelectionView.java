package net.sf.anathema.framework.swing.selection;

import net.miginfocom.layout.CC;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.Color;
import java.awt.SystemColor;

public class SwingObjectSelectionView<V> implements ObjectSelectionView<V>, AdditiveView {

  private final IChangeableJComboBox<V> comboBox;
  private final JLabel label;
  private Color disabledLabelColor = SystemColor.textInactiveText;

  public SwingObjectSelectionView(String labelString, ListCellRenderer renderer) {
    this.label = new JLabel(labelString);
    this.comboBox = new ChangeableJComboBox<>();
    setCellRenderer(renderer);
    setSelectedObject(null);
  }

  public void setCellRenderer(ListCellRenderer renderer) {
    comboBox.setRenderer(renderer);
  }

  @Override
  public void addTo(JPanel panel, CC data) {
    panel.add(label);
    panel.add(comboBox.getComponent(), data);
  }

  @Override
  public void setSelectedObject(V object) {
    comboBox.setSelectedObject(object);
  }

  @Override
  public void setObjects(V[] objects) {
    comboBox.setObjects(objects);
  }

  @Override
  public void addObjectSelectionChangedListener(ObjectValueListener<V> listener) {
    comboBox.addObjectSelectionChangedListener(listener);
  }

  @Override
  public void removeObjectSelectionChangedListener(ObjectValueListener<V> listener) {
    comboBox.removeObjectSelectionChangeListener(listener);
  }

  @Override
  public void setEnabled(boolean enabled) {
    if (enabled) {
      label.setForeground(SystemColor.textText);
    }
    else {
      label.setForeground(disabledLabelColor);
    }
    comboBox.getComponent().setEnabled(enabled);
  }

  @Override
  public V getSelectedObject() {
    return comboBox.getSelectedObject();
  }

  @Override
  public boolean isObjectSelected() {
    return getSelectedObject() != null;
  }
}