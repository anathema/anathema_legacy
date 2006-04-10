package net.sf.anathema.character.library.quality.presenter;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IQualityModel<Q extends IQuality> {

  public void setCurrentQuality(Q quality);

  public Q getCurrentQuality();

  public boolean isSelectable(Q quality);

  public boolean isActive(IQualitySelection<Q> selection);

  public IQualitySelection<Q>[] getSelectedQualities();

  public void removeQualitySelection(IQualitySelection<Q> selection);

  public void addQualitySelection(IQualitySelection<Q> selection);

  public boolean isCharacterExperienced();

  public void addModelChangeListener(IChangeListener listener);

  public Q[] getAvailableQualities();
}