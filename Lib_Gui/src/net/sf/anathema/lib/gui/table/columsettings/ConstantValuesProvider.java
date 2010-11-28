// Copyright (c) 2005 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.columsettings;

//NOT_PUBLISHED
public class ConstantValuesProvider implements IComboBoxValuesProvider {

  private final Object[] values;

  public ConstantValuesProvider(Object[] values) {
    this.values = values;
  }

  public Object[] getValues(int rowIndex) {
    return values;
  }
}