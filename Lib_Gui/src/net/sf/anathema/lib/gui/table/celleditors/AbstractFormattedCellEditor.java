//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.celleditors;

import java.text.Format;
import java.text.ParseException;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.DefaultFormatterFactory;

public abstract class AbstractFormattedCellEditor extends
		AbstractDelegatingCellEditor {

	public AbstractFormattedCellEditor(Format format,
			NullValueStrategy nullValueStrategy) {
		AbstractFormatter formatter = createFormatter(format);
		formatter = nullValueStrategy.decorateFormatter(formatter);
		((JFormattedTextField) getEditorComponent())
				.setFormatterFactory(new DefaultFormatterFactory(formatter));
	}

	protected abstract AbstractFormatter createFormatter(Format format);

	@Override
	protected final EditorDelegate createDelegate(JComponent editorComponent) {
		final JFormattedTextField textField = (JFormattedTextField) editorComponent;
		return new EditorDelegate(this) {
			private static final long serialVersionUID = -6355913286609133095L;

			@Override
			public void setValue(Object value) {
				textField.setValue(value);
			}

			@Override
			public Object getCellEditorValue() {
				try {
					textField.commitEdit();
				} catch (ParseException e) {
					// nothing to do
				}
				return textField.getValue();
			}
		};
	}

	@Override
	protected final JComponent createEditorComponent() {
		return new JFormattedTextField();
	}

}