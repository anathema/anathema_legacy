package net.sf.anathema.character.impl.view.advantage;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;

public class BackgroundSelectionView<V> extends ButtonControlledComboEditView<V>
{
  private final ChangeableJComboBox<V> detailBox;
  private final ITextFieldComboBoxEditor editor;
  
  public BackgroundSelectionView(Icon addIcon, String labelText,
		  ListCellRenderer renderer, ITextFieldComboBoxEditor backgroundEditor,
		  ITextFieldComboBoxEditor detailEditor)
  {
	super(addIcon, labelText, renderer);
    this.comboBox.getComponent().setEditable(true);
    this.comboBox.getComponent().setEditor(backgroundEditor);
    this.detailBox = new ChangeableJComboBox<V>(false);
//    detailBox.setRenderer(renderer);
    this.detailBox.getComponent().setEditable(true);
    this.detailBox.getComponent().setEditor(detailEditor);
    editor = detailEditor;
  }
  
  public void addEditChangedListener(final IObjectValueChangedListener<String> listener)
  {
	    editor.getEditorComponent().getDocument().addDocumentListener(new DocumentListener() {
	        public void changedUpdate(DocumentEvent e) {
	          listener.valueChanged(editor.getEditorComponent().getText());
	        }

	        public void insertUpdate(DocumentEvent e) {
	          listener.valueChanged(editor.getEditorComponent().getText());
	        }

	        public void removeUpdate(DocumentEvent e) {
	          listener.valueChanged(editor.getEditorComponent().getText());
	        }
	      });
  }
  
  public void clear()
  {
	  super.clear();
	  editor.getEditorComponent().setText("");
  }

  /** GridDialogLayout, 3 columns */
  public void addComponents(JPanel panel) {
    panel.add(comboBox.getComponent(), IGridDialogLayoutData.DEFAULT);
    panel.add(detailBox.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(addButton, GridDialogLayoutData.RIGHT);
  }
}