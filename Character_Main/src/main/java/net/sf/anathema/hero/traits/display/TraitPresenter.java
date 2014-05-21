package net.sf.anathema.hero.traits.display;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.lib.control.IntValueChangedListener;

public class TraitPresenter {

  private final Trait trait;
  private final IntValueView view;

  public TraitPresenter(Trait trait, IntValueView view) {
    this.trait = trait;
    this.view = view;
  }

  public void initPresentation() {
    view.setValue(trait.getCurrentValue());
    initModelValueListening();
    initViewValueListening();
  }

  private void initModelValueListening() {
    trait.addCurrentValueListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        view.setValue(newValue);
      }
    });
  }

  private void initViewValueListening() {
    view.addIntValueChangedListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        trait.setCurrentValue(newValue);
      }
    });
  }
}
