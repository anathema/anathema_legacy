//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.celleditors;

import javax.swing.JComponent;
import javax.swing.JTextField;

// NOT_PUBLISHED
public class StringCellEditor extends AbstractDelegatingCellEditor {
	private static final long serialVersionUID = -3453009429361817177L;

	@Override
	protected final EditorDelegate createDelegate(JComponent editorComponent) {
		final JTextField textField = (JTextField) editorComponent;
		return new EditorDelegate(this) {
			private static final long serialVersionUID = -3453009429361817177L;

			@Override
			public void setValue(Object value) {
				textField.setText(value != null ? value.toString() : ""); //$NON-NLS-1$
				textField.selectAll();
			}

			@Override
			public Object getCellEditorValue() {
				return textField.getText();
			}
		};
	}

	@Override
	protected JComponent createEditorComponent() {
		return new JTextField();
	}
}