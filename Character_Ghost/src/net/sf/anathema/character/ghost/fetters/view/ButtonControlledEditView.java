package net.sf.anathema.character.ghost.fetters.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class ButtonControlledEditView implements IView {
  private final JButton addButton;
  private final JTextField text;

  public ButtonControlledEditView(Icon addIcon) {
    this.text = new JTextField(30);
    this.addButton = new JButton(null, addIcon);
    addButton.setPreferredSize(new Dimension(addIcon.getIconWidth() + 4, addIcon.getIconHeight() + 4));
  }

  @Override
  public JPanel getComponent() {
    JPanel panel = new JPanel(new MigLayout(withoutInsets()));
    panel.add(text, new CC().growX().pushX());
    panel.add(addButton, LayoutUtils.constraintsForImageButton(addButton));
    return panel;
  }

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

  public void addButtonListener(ActionListener listener) {
    addButton.addActionListener(listener);
  }

  public void clear() {
    text.setText(null);
  }

  public void setButtonEnabled(boolean enabled) {
    addButton.setEnabled(enabled);
  }
}