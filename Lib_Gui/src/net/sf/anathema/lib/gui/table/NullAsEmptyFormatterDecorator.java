//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table;

import java.text.ParseException;

import javax.swing.JFormattedTextField.AbstractFormatter;

// NOT_PUBLISHED
public class NullAsEmptyFormatterDecorator extends AbstractFormatter {

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