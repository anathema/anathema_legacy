package net.sf.anathema.framework.module.preferences;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.event.ActionListener;

public class LabelledPreferenceComboBox<T> {
  private final JLabel label;
  private final JComboBox box;

  public LabelledPreferenceComboBox(String labelText, ListCellRenderer renderer, T[] values) {
    this.label = new JLabel(labelText);
    this.box = new JComboBox(values);
    box.setRenderer(renderer);
  }

  public void setSelectedItem(T value) {
    box.setSelectedItem(value);
  }

  public void addTo(JPanel panel) {
    panel.add(label);
    panel.add(box);
  }

  public void addActionListener(ActionListener listener) {
    box.addActionListener(listener);
  }

  public T getSelectedItem() {
    return (T) box.getSelectedItem();
  }
}