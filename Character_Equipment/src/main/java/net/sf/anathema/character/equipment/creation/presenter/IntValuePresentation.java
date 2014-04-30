package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.fx.hero.configurableview.IIntegerSpinner;
import net.sf.anathema.lib.control.IntValueChangedListener;

public class IntValuePresentation {

  public void initPresentation(IIntegerSpinner integerSpinner, IIntValueModel intValueModel) {
    intValueModel.addIntValueChangeListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        integerSpinner.setValue(newValue);
      }
    });
    integerSpinner.setMinimum(intValueModel.getMinimum());
    integerSpinner.setMaximum(intValueModel.getMaximum());
    integerSpinner.setValue(intValueModel.getValue());
    // SpinnerListening umbedingt erst nach Initialisierung des Spinners durchfuehren, weil dieser
    // sonst bei setMinimum potentiell den value auf Minimum setzt und der Startwert verloren geht.
    integerSpinner.addChangeListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        intValueModel.setValue(newValue);
      }
    });
  }
}