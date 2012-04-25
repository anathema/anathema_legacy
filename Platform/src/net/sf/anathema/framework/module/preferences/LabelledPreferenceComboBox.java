package net.sf.anathema.framework.module.preferences;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.IDialogComponent;

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

  public IDialogComponent getDialogComponent() {
    return new IDialogComponent() {
      @Override
      public int getColumnCount() {
        return 2;
      }

      @Override
      public void fillInto(JPanel panel, int columnCount) {
        panel.add(label);
        panel.add(box);
      }
    };
  }

  public void addActionListener(ActionListener listener) {
    box.addActionListener(listener);
  }

  public T getSelectedItem() {
    return (T) box.getSelectedItem();
  }

}
