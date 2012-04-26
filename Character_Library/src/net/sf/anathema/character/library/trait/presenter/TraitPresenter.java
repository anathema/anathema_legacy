package net.sf.anathema.character.library.trait.presenter;

import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.gui.Presenter;

public class TraitPresenter implements Presenter {

  private final ITrait trait;
  private final IIntValueView view;

  public TraitPresenter(ITrait trait, IIntValueView view) {
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
    trait.accept(new ITraitVisitor() {
      @Override
      public void visitDefaultTrait(final IDefaultTrait visitedTrait) {
        visitedTrait.addRangeListener(new IChangeListener() {
          @Override
          public void changeOccurred() {
            view.setMaximum(visitedTrait.getMaximalValue());
          }
        });
      }

      @Override
      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        // nothing to do
      }
    });
  }

  private void initViewValueListening() {
    trait.accept(new ITraitVisitor() {
      @Override
      public void visitDefaultTrait(final IDefaultTrait visitedTrait) {
        view.addIntValueChangedListener(new IIntValueChangedListener() {
          @Override
          public void valueChanged(int newValue) {
            visitedTrait.setCurrentValue(newValue);
          }
        });
      }

      @Override
      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        // nothing to do
      }
    });
  }
}
