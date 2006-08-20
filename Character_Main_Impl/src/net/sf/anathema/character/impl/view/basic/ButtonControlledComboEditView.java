package net.sf.anathema.character.impl.view.basic;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.view.basic.IButtonControlledComboEditView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;

public class ButtonControlledComboEditView<V> implements IButtonControlledComboEditView<V> {

  private final ChangeableJComboBox<V> comboBox;
  private final JButton addButton;
  private final JTextField text;

  public ButtonControlledComboEditView(V[] objects, Icon addIcon) {
    this.comboBox = new ChangeableJComboBox<V>(objects, false);
    this.text = new JTextField();
    this.addButton = new JButton(null, addIcon);
    addButton.setPreferredSize(new Dimension(addIcon.getIconWidth() + 4, addIcon.getIconHeight() + 4));
  }

  public void addTo(GridDialogPanel panel, final String labelText, final ListCellRenderer renderer) {
    panel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 3;
      }

      public void fillInto(JPanel layoutedPanel, int columnCount) {
        comboBox.setRenderer(renderer);
        layoutedPanel.add(new JLabel(labelText));
        layoutedPanel.add(new EndOfLineMarkerComponent());
        layoutedPanel.add(comboBox.getComponent());
        layoutedPanel.add(text, GridDialogLayoutData.FILL_HORIZONTAL);
        layoutedPanel.add(addButton, GridDialogLayoutData.RIGHT);
      }
    });
  }

  public void setSelectedObject(V object) {
    comboBox.setSelectedObject(object);
  }

  public void setText(String text) {
    this.text.setText(text);
  }

  public void addEditChangedListener(final IObjectValueChangedListener<String> listener) {
    text.getDocument().addDocumentListener(new DocumentListener() {
      public void changedUpdate(DocumentEvent e) {
        listener.valueChanged(text.getText());
      }

      public void insertUpdate(DocumentEvent e) {
        listener.valueChanged(text.getText());
      }

      public void removeUpdate(DocumentEvent e) {
        listener.valueChanged(text.getText());
      }
    });
  }

  public void addSelectionChangedListener(final IObjectValueChangedListener<V> listener) {
    comboBox.addObjectSelectionChangedListener(listener);
  }

  public void addButtonListener(ActionListener listener) {
    addButton.addActionListener(listener);
  }

  public void clear() {
    comboBox.setSelectedObject(null);
    text.setText(null);
  }
  
  public void setButtonEnabled(boolean enabled) {
    addButton.setEnabled(enabled);
  }
}