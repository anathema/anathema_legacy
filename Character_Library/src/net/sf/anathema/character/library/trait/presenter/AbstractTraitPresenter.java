package net.sf.anathema.character.library.trait.presenter;

import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public abstract class AbstractTraitPresenter {

  protected AbstractTraitPresenter() {
    // nothing to do
  }

  protected final void addModelValueListener(final IModifiableTrait trait, final IIntValueView view) {
    trait.addCurrentValueListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        view.setValue(newValue);
      }
    });
    trait.addRangeListener(new IChangeListener() {
      public void changeOccured() {
        view.setMaximum(trait.getMaximalValue());
      }
    });
  }

  protected final void addViewValueListener(IIntValueView view, final IModifiableTrait trait) {
    view.addIntValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        trait.setCurrentValue(newValue);
      }
    });
  }
}