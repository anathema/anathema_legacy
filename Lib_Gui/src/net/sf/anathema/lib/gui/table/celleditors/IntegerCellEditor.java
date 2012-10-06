package net.sf.anathema.lib.gui.table.celleditors;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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

			@Override
			public void setValue(Object value) {
				spinner.setModel(spinnerNumberModel);
				spinnerNumberModel.setValue(value);
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