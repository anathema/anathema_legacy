package net.sf.anathema.lib.gui.table.celleditors;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public class NumbersOnlyIntegerCellEditor extends AbstractDelegatingCellEditor {

  private IntegerSpinner spinner;

  public NumbersOnlyIntegerCellEditor(int minimum, int maximum, int stepsize) {
    SpinnerNumberModel model = (SpinnerNumberModel) ((JSpinner) spinner.getComponent()).getModel();
    model.setMinimum(minimum);
    model.setMaximum(maximum);
    model.setStepSize(stepsize);
  }

  @Override
  protected EditorDelegate createDelegate(JComponent editor) {
    return new EditorDelegate(this) {
      @Override
      public void setValue(Object value) {
        spinner.setValue(value);
      }

      @Override
      public Object getCellEditorValue() {
        return spinner.getValue();
      }
    };
  }

  @Override
  protected JComponent createEditorComponent() {
    spinner = new IntegerSpinner(0);
    return spinner.getComponent();
  }
}