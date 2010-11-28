package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public abstract class AbstractTraitView implements IIntValueView {

  private final IIntValueDisplay valueDisplay;
  private final String labelText;

  public AbstractTraitView(IIntValueDisplayFactory factory, String labelText, int value, int maxValue) {
    this.labelText = labelText;
    this.valueDisplay = factory.createIntValueDisplay(maxValue, value);
  }

  public void setValue(int newValue) {
    valueDisplay.setValue(newValue);
  }

  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    valueDisplay.addIntValueChangedListener(listener);
  }

  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    valueDisplay.removeIntValueChangedListener(listener);
  }

  protected String getLabelText() {
    return labelText;
  }

  protected IIntValueDisplay getValueDisplay() {
    return valueDisplay;
  }

  public void setMaximum(int maximalValue) {
    valueDisplay.setMaximum(maximalValue);
  }
}