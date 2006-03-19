package net.sf.anathema.character.library.quality.presenter;

public interface IQualitySelection<Q extends IQuality> {

  public Q getQuality();

  public int getPointValue();

  boolean isCreationActive();

  boolean isExperienceActive();

  public void setExperienceActive(boolean active);

  public void setCreationActive(boolean active);
}