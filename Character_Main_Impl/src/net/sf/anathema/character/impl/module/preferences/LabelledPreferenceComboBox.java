package net.sf.anathema.character.impl.module.preferences;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.IDialogComponent;

public class LabelledPreferenceComboBox {

  private final JLabel label;
  private final JComboBox box;

  public LabelledPreferenceComboBox(String labelText, ListCellRenderer renderer, Object[] values) {
    this.label = new JLabel(labelText);
    this.box = new JComboBox(values);
    box.setRenderer(renderer);
  }

  public void setSelectedItem(Object value) {
    box.setSelectedItem(value);
  }

  public IDialogComponent getDialogComponent() {
    return new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        panel.add(label);
        panel.add(box);
      }
    };
  }

  public void addActionListener(ActionListener listener) {
    box.addActionListener(listener);
  }

  public Object getSelectedItem() {
    return box.getSelectedItem();
  }

}