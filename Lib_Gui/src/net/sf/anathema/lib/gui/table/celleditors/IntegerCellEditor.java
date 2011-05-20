// Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.celleditors;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

// NOT_PUBLISHED
public class IntegerCellEditor extends AbstractDelegatingCellEditor {

	private final SpinnerNumberModel spinnerNumberModel;

	public IntegerCellEditor(int minimum, int maximum, int stepsize) {
		spinnerNumberModel = new SpinnerNumberModel(minimum, minimum, maximum,
				stepsize);
	}

	@Override
	protected final EditorDelegate createDelegate(JComponent editorComponent) {
		final JSpinner spinner = (JSpinner) editorComponent;
		return new EditorDelegate(this) {
			private static final long serialVersionUID = -8488347134426404968L;

			@Override
			public void setValue(Object value) {
				spinner.setModel(spinnerNumberModel);
				spinnerNumberModel.setValue(value);

				// selectall() Does not work :-(
				// JComponent editor = spinner.getEditor();
				// if (editor instanceof JSpinner.DefaultEditor) {
				// JSpinner.DefaultEditor defaultEditor =(DefaultEditor) editor;
				// defaultEditor.getTextField().selectAll();
				// }
			}

			@Override
			public Object getCellEditorValue() {
				return spinnerNumberModel.getNumber();
			}
		};
	}

	@Override
	protected JComponent createEditorComponent() {
		return new JSpinner();
	}
}