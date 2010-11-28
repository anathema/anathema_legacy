package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.charmentry.presenter.ITraitSelectionChangedListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface ISelectableTraitView {

  public void setSelectableTraits(IIdentificate[] traits);

  public void addTraitSelectionListener(ITraitSelectionChangedListener listener);

  public void setValue(int value);

  public void setSelectedTrait(IIdentificate object);
}