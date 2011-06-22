package net.sf.anathema.framework.presenter.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;

public class ButtonControlledComboEditView<V> implements IButtonControlledComboEditView<V>, IView {

  protected final ChangeableJComboBox<V> comboBox;
  protected final JLabel label;
  protected final JButton addButton;
  protected final JTextField text;
  
  public ButtonControlledComboEditView(Icon addIcon, ListCellRenderer renderer)
  {
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

  public JPanel getComponent() {
    JPanel panel = new JPanel(new GridDialogLayout(3 + (label != null ? 1 : 0), false));
    if (label != null)
    	panel.add(label);
    panel.add(comboBox.getComponent(), IGridDialogLayoutData.DEFAULT);
    panel.add(text, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(addButton, GridDialogLayoutData.RIGHT);
    return panel;
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

  public void setObjects(V[] objects) {
    comboBox.setObjects(objects);
  }

  public void addSelectionChangedListener(final IObjectValueChangedListener<V> listener) {
    comboBox.addObjectSelectionChangedListener(listener);
  }

  public void addButtonListener(ActionListener listener) {
    addButton.addActionListener(listener);
  }
  
  public void addButtonListener(final IObjectValueChangedListener<V> listener) {
	    addButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        listener.valueChanged(comboBox.getSelectedObject());
	      }
	    });
	  }

  public void clear() {
    comboBox.setSelectedObject(null);
    text.setText(null);
  }

  public void setButtonEnabled(boolean enabled) {
    addButton.setEnabled(enabled);
  }
}