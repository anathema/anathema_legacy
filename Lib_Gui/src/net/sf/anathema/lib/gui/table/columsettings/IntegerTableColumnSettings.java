// Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.columsettings;

import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import net.sf.anathema.lib.gui.table.celleditors.NumbersOnlyIntegerCellEditor;

// NOT_PUBLISHED
public class IntegerTableColumnSettings extends AbstractTableColumnSettings {

  private final int stepsize;
  private final int maximum;
  private final int minimum;
  private final Color negativeColor;
  private final TableCellRenderer rightRenderer = new DefaultTableCellRenderer() {
	private static final long serialVersionUID = 2316992862338590339L;

	{
      setHorizontalAlignment(SwingConstants.RIGHT);
    }

    @Override
    public Component getTableCellRendererComponent(
        javax.swing.JTable table,
        Object value,
        boolean isSelected,
        boolean hasFocus,
        int row,
        int column) {
      Component renderComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      if (value instanceof Integer) {
        if (((Integer) value).intValue() < 0) {
          renderComponent.setForeground(negativeColor);
        }
        else {
          renderComponent.setForeground(table.getForeground());
        }
      }
      return renderComponent;
    }
  };

  public IntegerTableColumnSettings(int minimum, int maximum, int stepsize) {
    this(minimum, maximum, stepsize, null);
  }

  public IntegerTableColumnSettings(int minimum, int maximum, int stepsize, Color negativeColor) {
    super(2 + Math.max(String.valueOf(minimum).length(), String.valueOf(maximum).length()));
    this.minimum = minimum;
    this.maximum = maximum;
    this.stepsize = stepsize;
    this.negativeColor = negativeColor;
  }

  public TableCellEditor getEditor() {
    return new NumbersOnlyIntegerCellEditor(minimum, maximum, stepsize);
  }

  @Override
  public TableCellRenderer getRenderer() {
    return rightRenderer;
  }
}