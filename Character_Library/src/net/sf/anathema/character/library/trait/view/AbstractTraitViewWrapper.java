package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public abstract class AbstractTraitViewWrapper implements ITraitView {

  private final ITraitView view;

  public AbstractTraitViewWrapper(ITraitView view) {
    this.view = view;
  }

  public void setMaximum(int maximalValue) {
    view.setMaximum(maximalValue);
  }

  public void setValue(int newValue) {
    view.setValue(newValue);
  }

  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    view.addIntValueChangedListener(listener);
  }

  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    view.addIntValueChangedListener(listener);
  }

  protected ITraitView getView() {
    return view;
  }
}