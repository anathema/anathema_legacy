package net.sf.anathema.character.impl.view.advantage;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.lib.control.ObjectValueListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;

public class BackgroundSelectionView<V> extends ButtonControlledComboEditView<V> {
  private final JTextField detailBox;
  private final JLabel label;

  public BackgroundSelectionView(Icon addIcon, String labelText, ListCellRenderer renderer,
                                 ITextFieldComboBoxEditor backgroundEditor) {
    super(addIcon, renderer);
    this.comboBox.getComponent().setEditable(true);
    this.comboBox.getComponent().setEditor(backgroundEditor);
    this.detailBox = new JTextField("");
    this.detailBox.setPreferredSize(new Dimension(220, detailBox.getPreferredSize().height));
    this.label = new JLabel(labelText);
    setButtonEnabled(false);
    enableButtonOnlyWhenComboboxHasContent(backgroundEditor);
  }

  private void enableButtonOnlyWhenComboboxHasContent(ITextFieldComboBoxEditor backgroundEditor) {
    backgroundEditor.getEditorComponent().getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        setButtonEnabled(e.getDocument().getLength() > 0);
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        setButtonEnabled(e.getDocument().getLength() > 0);
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        setButtonEnabled(e.getDocument().getLength() > 0);
      }
    });
  }

  @Override
  public void addEditChangedListener(final ObjectValueListener<String> listener) {
    detailBox.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void changedUpdate(DocumentEvent e) {
        listener.valueChanged(detailBox.getText());
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        listener.valueChanged(detailBox.getText());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        listener.valueChanged(detailBox.getText());
      }
    });
  }

  @Override
  public void clear() {
    super.clear();
    detailBox.setText("");
  }

  @Override
  public JPanel getComponent() {
    JPanel panel = new JPanel(new GridDialogLayout(4, false));
    panel.add(label);
    panel.add(comboBox.getComponent(), IGridDialogLayoutData.DEFAULT);
    panel.add(detailBox, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(addButton, GridDialogLayoutData.RIGHT);
    return panel;
  }
}