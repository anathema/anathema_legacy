package net.sf.anathema.character.library.trait.presenter;

import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.gui.Presenter;

public class TraitPresenter implements Presenter {

  private final IDefaultTrait trait;
  private final IIntValueView view;

  public TraitPresenter(IDefaultTrait trait, IIntValueView view) {
    this.trait = trait;
    this.view = view;
  }

  @Override
  public void initPresentation() {
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
    trait.addRangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        view.setMaximum(trait.getMaximalValue());
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
