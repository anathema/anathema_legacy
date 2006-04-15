package net.sf.anathema.charmentry.presenter;

import net.sf.anathema.charmentry.view.IContentView;

public interface ISelectableTraitView extends IContentView {

  public void setSelectableTraits(Object[] traits);

  public void addTraitSelectionListener(ITraitSelectionChangedListener listener);

  public void setValue(int value);

  public void setSelectedTrait(Object object);

}