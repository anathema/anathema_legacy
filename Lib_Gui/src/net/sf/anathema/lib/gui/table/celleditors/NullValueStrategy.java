//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.celleditors;

import javax.swing.JFormattedTextField.AbstractFormatter;

import net.sf.anathema.lib.gui.table.NullAsEmptyFormatterDecorator;

// NOT_PUBLISHED
public abstract class NullValueStrategy {

  public static final NullValueStrategy EMPTY = new NullValueStrategy() {
    @Override
    public String getStringValue() {
      return ""; //$NON-NLS-1$
    }

    @Override
    public AbstractFormatter decorateFormatter(AbstractFormatter formatter) {
      return new NullAsEmptyFormatterDecorator(formatter);
    }
  };
  public static final NullValueStrategy DISALLOW = new NullValueStrategy() {
    @Override
    public String getStringValue() {
      throw new NullPointerException("null value not allowed"); //$NON-NLS-1$
    }

    @Override
    public AbstractFormatter decorateFormatter(AbstractFormatter formatter) {
      return formatter;
    }
  };

  private NullValueStrategy() {
    // nothing to do
  }

  public abstract String getStringValue();

  public abstract AbstractFormatter decorateFormatter(AbstractFormatter formatter);

}