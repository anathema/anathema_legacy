package net.sf.anathema.framework.presenter.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class ButtonControlledComboEditView<V> implements IButtonControlledComboEditView<V>, IView {
  protected final ChangeableJComboBox<V> comboBox;
  protected final JButton addButton;
  protected final JTextField text;

  public ButtonControlledComboEditView(Icon addIcon, ListCellRenderer renderer) {
    this.comboBox = new ChangeableJComboBox<V>(false);
    comboBox.setRenderer(renderer);
    this.text = new JTextField(30);
    this.addButton = new JButton(null, addIcon);
    addButton.setPreferredSize(new Dimension(addIcon.getIconWidth() + 4, addIcon.getIconHeight() + 4));
  }

  @Override
  public JPanel getComponent() {
    JPanel panel = new JPanel(new MigLayout(withoutInsets()));
    panel.add(comboBox.getComponent(), new CC().minWidth("70"));
    panel.add(text, new CC().growX().pushX());
    panel.add(addButton, LayoutUtils.constraintsForImageButton(addButton));
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