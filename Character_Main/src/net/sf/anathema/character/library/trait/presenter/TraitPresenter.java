package net.sf.anathema.character.library.trait.presenter;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.gui.Presenter;

public class TraitPresenter implements Presenter {

  private final Trait trait;
  private final IIntValueView view;

  public TraitPresenter(Trait trait, IIntValueView view) {
    this.trait = trait;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    view.setValue(trait.getCurrentValue());
    initModelValueListening();
    initViewValueListening();
  }

  private void initModelValueListening() {
    trait.addCurrentValueListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        view.setValue(newValue);
      }
    });
  }

  private void initViewValueListening() {
    view.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        trait.setCurrentValue(newValue);
      }
    });
  }
}
