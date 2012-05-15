package net.sf.anathema.character.library.quality.presenter;

import net.sf.anathema.lib.control.IChangeListener;

public interface IQualityModel<Q extends IQuality> {

  void setCurrentQuality(Q quality);

  Q getCurrentQuality();

  boolean isSelectable(Q quality);

  boolean isActive(IQualitySelection<Q> selection);

  IQualitySelection<Q>[] getSelectedQualities();

  void removeQualitySelection(IQualitySelection<Q> selection);

  void addQualitySelection(IQualitySelection<Q> selection);

  boolean isCharacterExperienced();

  void addModelChangeListener(IChangeListener listener);

  Q[] getAvailableQualities();
}