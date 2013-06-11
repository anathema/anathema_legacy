package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.lib.control.IIntValueChangedListener;

public abstract class AbstractTraitViewWrapper<K extends ITraitView<?>> implements ITraitView<K> {

  private final K view;

  public AbstractTraitViewWrapper(K view) {
    this.view = view;
  }

  @Override
  public void setMaximum(int maximalValue) {
    view.setMaximum(maximalValue);
  }

  @Override
  public void setValue(int newValue) {
    view.setValue(newValue);
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    view.addIntValueChangedListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    view.addIntValueChangedListener(listener);
  }

  @Override
  public K getInnerView() {
    return view;
  }
}