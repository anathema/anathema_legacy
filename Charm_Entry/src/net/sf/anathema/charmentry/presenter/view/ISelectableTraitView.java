package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.charmentry.presenter.ITraitSelectionChangedListener;

public interface ISelectableTraitView {

  public void setSelectableTraits(Object[] traits);

  public void addTraitSelectionListener(ITraitSelectionChangedListener listener);

  public void setValue(int value);

  public void setSelectedTrait(Object object);
}