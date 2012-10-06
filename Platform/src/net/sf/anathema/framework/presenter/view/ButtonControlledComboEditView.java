package net.sf.anathema.framework.presenter.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonControlledComboEditView<V> implements IButtonControlledComboEditView<V>, IView {
  protected final ChangeableJComboBox<V> comboBox;
  protected final JLabel label;
  protected final JButton addButton;
  protected final JTextField text;

  public ButtonControlledComboEditView(Icon addIcon, ListCellRenderer renderer) {
    this(addIcon, null, renderer);
  }

  public ButtonControlledComboEditView(Icon addIcon, String labelText, ListCellRenderer renderer) {
    this.comboBox = new ChangeableJComboBox<V>(false);
    comboBox.setRenderer(renderer);
    this.text = new JTextField(30);
    this.addButton = new JButton(null, addIcon);
    addButton.setPreferredSize(new Dimension(addIcon.getIconWidth() + 4, addIcon.getIconHeight() + 4));
    this.label = labelText != null ? new JLabel(labelText) : null;
  }

  @Override
  public JPanel getComponent() {
    JPanel panel = new JPanel(new GridDialogLayout(3 + (label != null ? 1 : 0), false));
    if (label != null) panel.add(label);
    panel.add(comboBox.getComponent(), IGridDialogLayoutData.DEFAULT);
    panel.add(text, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(addButton, GridDialogLayoutData.RIGHT);
    return panel;
  }

  @Override
  public void addEditChangedListener(final ObjectValueListener<String> listener) {
    text.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void changedUpdate(DocumentEvent e) {
        listener.valueChanged(text.getText());
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        listener.valueChanged(text.getText());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        listener.valueChanged(text.getText());
      }
    });
  }

  @Override
  public void setObjects(V[] objects) {
    comboBox.setObjects(objects);
  }

  @Override
  public void addSelectionChangedListener(ObjectValueListener<V> listener) {
    comboBox.addObjectSelectionChangedListener(listener);
  }

  @Override
  public void addButtonListener(ActionListener listener) {
    addButton.addActionListener(listener);
  }

  @Override
  public void addButtonListener(final ObjectValueListener<V> listener) {
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(comboBox.getSelectedObject());
      }
    });
  }

  @Override
  public void clear() {
    comboBox.setSelectedObject(null);
    text.setText(null);
  }

  @Override
  public void setButtonEnabled(boolean enabled) {
    addButton.setEnabled(enabled);
  }
}