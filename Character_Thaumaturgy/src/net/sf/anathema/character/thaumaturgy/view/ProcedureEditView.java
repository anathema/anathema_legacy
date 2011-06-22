package net.sf.anathema.character.thaumaturgy.view;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;

public class ProcedureEditView<V> extends ButtonControlledComboEditView<V> implements IProcedureEditView<V>
{
  private final SimpleTraitView procedureControl;
  private final ChangeableJComboBox<V> procedureBox;
  private final ITextFieldComboBoxEditor editor;
  
  public ProcedureEditView(Icon addIcon, SimpleTraitView trait,
		  ListCellRenderer renderer, ITextFieldComboBoxEditor artEditor,
		  ITextFieldComboBoxEditor procedureEditor) {
	super(addIcon, renderer);
    this.procedureControl = trait;
    this.comboBox.getComponent().setEditable(true);
    this.comboBox.getComponent().setEditor(artEditor);
    this.procedureBox = new ChangeableJComboBox<V>(false);
    procedureBox.setRenderer(renderer);
    this.procedureBox.getComponent().setEditable(true);
    this.procedureBox.getComponent().setEditor(procedureEditor);
    editor = procedureEditor;
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
  
  @SuppressWarnings("unchecked")
  public void clear()
  {
	  super.clear();
	  procedureBox.setObjects((V[]) new Object[0]);
	  editor.getEditorComponent().setText("");
  }
  
  public void setProcedures(V[] procedures)
  {
	  procedureBox.setObjects(procedures);
  }

  /** GridDialogLayout, 4 columns */
  public void addComponents(JPanel panel) {
    panel.add(comboBox.getComponent(), IGridDialogLayoutData.DEFAULT);
    procedureControl.addComponents(panel);
    panel.add(procedureBox.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(addButton, GridDialogLayoutData.RIGHT);
  }
}