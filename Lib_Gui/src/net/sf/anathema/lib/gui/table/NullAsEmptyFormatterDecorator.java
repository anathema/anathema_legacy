package net.sf.anathema.lib.gui.table;

import javax.swing.JFormattedTextField.AbstractFormatter;
import java.text.ParseException;

public class NullAsEmptyFormatterDecorator extends AbstractFormatter {

	private static final long serialVersionUID = 2539156433587133643L;
	private final AbstractFormatter delegate;

	public NullAsEmptyFormatterDecorator(AbstractFormatter delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object stringToValue(String text) throws ParseException {
		return text.trim().length() == 0 ? null : delegate.stringToValue(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		return value == null ? "" : delegate.valueToString(value); //$NON-NLS-1$
	}
}