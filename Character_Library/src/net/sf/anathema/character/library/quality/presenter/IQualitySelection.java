package net.sf.anathema.character.library.quality.presenter;

public interface IQualitySelection<Q extends IQuality> {

  Q getQuality();

  int getPointValue();

  boolean isCreationActive();

  boolean isExperienceActive();

  void setExperienceActive(boolean active);

  void setCreationActive(boolean active);
}