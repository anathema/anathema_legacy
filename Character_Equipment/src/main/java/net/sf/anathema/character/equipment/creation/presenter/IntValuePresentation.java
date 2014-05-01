package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.fx.hero.configurableview.IIntegerSpinner;
import net.sf.anathema.lib.control.IntValueChangedListener;

public class IntValuePresentation {

  public void initPresentation(IIntegerSpinner integerSpinner, IIntValueModel intValueModel) {
    integerSpinner.setValue(intValueModel.getValue());
    integerSpinner.setMinimum(intValueModel.getMinimum());
    integerSpinner.setMaximum(intValueModel.getMaximum());
    intValueModel.addIntValueChangeListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        integerSpinner.setValue(newValue);
      }
    });
    integerSpinner.addChangeListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        intValueModel.setValue(newValue);
      }
    });
  }
}