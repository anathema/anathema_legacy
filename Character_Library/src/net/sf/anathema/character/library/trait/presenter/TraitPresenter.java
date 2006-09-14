package net.sf.anathema.character.library.trait.presenter;

import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;

public class TraitPresenter implements IPresenter {

  private final ITrait trait;
  private final IIntValueView view;

  public TraitPresenter(ITrait trait, IIntValueView view) {
    this.trait = trait;
    this.view = view;
  }

  public void initPresentation() {
    initModelValueListening();
    initViewValueListening();
  }

  private void initModelValueListening() {
    trait.addCurrentValueListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        view.setValue(newValue);
      }
    });
    trait.accept(new ITraitVisitor() {
      public void visitDefaultTrait(final IDefaultTrait visitedTrait) {
        visitedTrait.addRangeListener(new IChangeListener() {
          public void changeOccured() {
            view.setMaximum(visitedTrait.getMaximalValue());
          }
        });
      }

      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        // nothing to do
      }
    });
  }

  private void initViewValueListening() {
    trait.accept(new ITraitVisitor() {
      public void visitDefaultTrait(final IDefaultTrait visitedTrait) {
        view.addIntValueChangedListener(new IIntValueChangedListener() {
          public void valueChanged(int newValue) {
            visitedTrait.setCurrentValue(newValue);
          }
        });
      }

      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        // nothing to do
      }
    });
  }
}