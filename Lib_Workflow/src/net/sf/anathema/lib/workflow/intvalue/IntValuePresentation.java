package net.sf.anathema.lib.workflow.intvalue;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public class IntValuePresentation {

  public void initView(final IntegerSpinner integerSpinner, final IIntValueModel intValueModel) {
    intValueModel.addIntValueChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        integerSpinner.setValue(newValue);
      }
    });
    integerSpinner.setMinimum(intValueModel.getMinimum());
    integerSpinner.setMaximum(intValueModel.getMaximum());
    integerSpinner.setValue(intValueModel.getValue());
    // SpinnerListening umbedingt erst nach Initialisierung des Spinners durchführen, weil dieser
    // sonst bei setMinimum potentiell den value auf Minimum setzt und der Startwert verloren geht.
    integerSpinner.addChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        intValueModel.setValue(newValue);
      }
    });
  }
}