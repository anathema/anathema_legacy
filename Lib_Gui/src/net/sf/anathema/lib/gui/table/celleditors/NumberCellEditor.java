//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.celleditors;

import java.text.Format;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.NumberFormatter;

public class NumberCellEditor extends AbstractFormattedCellEditor {

  private NumberFormatter formatter;

  public NumberCellEditor(NumberFormat format, Class<?> valueClass, NullValueStrategy nullValueStrategy) {
    super(format, nullValueStrategy);
    formatter.setValueClass(valueClass);
  }

  @Override
  protected AbstractFormatter createFormatter(Format format) {
    formatter = new NumberFormatter((NumberFormat) format);
    formatter.setAllowsInvalid(false);
    formatter.setCommitsOnValidEdit(true);
    return formatter;
  }

}