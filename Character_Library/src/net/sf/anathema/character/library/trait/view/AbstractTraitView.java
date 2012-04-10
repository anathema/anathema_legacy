package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.framework.value.IIntValueDisplayFactory;
import net.sf.anathema.framework.value.NullUpperBounds;
import net.sf.anathema.character.library.intvalue.TraitUpperBounds;
import net.sf.anathema.framework.value.TwoUpperBounds;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public abstract class AbstractTraitView implements IIntValueView {

  private final IIntValueDisplay valueDisplay;
  private final String labelText;

  public AbstractTraitView(IIntValueDisplayFactory factory, String labelText, int value, int maxValue, IModifiableCapTrait trait) {
    this.labelText = labelText;
    TwoUpperBounds bounds = createBounds(trait);
    this.valueDisplay = maxValue > 0 ? factory.createIntValueDisplay(maxValue, value, bounds) : null;
  }

  private TwoUpperBounds createBounds(IModifiableCapTrait trait) {
    if (trait == null) {
      return new NullUpperBounds();
    }
    return new TraitUpperBounds(trait);
  }

  @Override
  public void setValue(int newValue) {
    if (valueDisplay != null) valueDisplay.setValue(newValue);
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    if (valueDisplay != null) valueDisplay.addIntValueChangedListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    if (valueDisplay != null) valueDisplay.removeIntValueChangedListener(listener);
  }

  protected String getLabelText() {
    return labelText;
  }

  protected IIntValueDisplay getValueDisplay() {
    return valueDisplay;
  }

  @Override
  public void setMaximum(int maximalValue) {
    valueDisplay.setMaximum(maximalValue);
  }
}