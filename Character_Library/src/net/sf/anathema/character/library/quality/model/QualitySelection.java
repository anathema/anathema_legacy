package net.sf.anathema.character.library.quality.model;

import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;

public class QualitySelection<Q extends IQuality> implements IQualitySelection<Q> {

  private final Q quality;
  private final int pointValue;
  private boolean creationActive;
  private boolean experienceActive;

  public QualitySelection(Q quality, int pointValue, boolean creationActive) {
    this(quality, pointValue, creationActive, true);
  }

  public QualitySelection(Q quality, int value, boolean creationActive, boolean experienceActive) {
    this.quality = quality;
    this.pointValue = value;
    this.creationActive = creationActive;
    this.experienceActive = experienceActive;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof QualitySelection)) {
      return false;
    }
    QualitySelection< ? > other = (QualitySelection< ? >) obj;
    return other.quality == quality
        && other.pointValue == pointValue
        && other.creationActive == creationActive
        && other.experienceActive == experienceActive;
  }

  @Override
  public int hashCode() {
    return quality.hashCode() + pointValue * 11;
  }

  public Q getQuality() {
    return quality;
  }

  public int getPointValue() {
    return pointValue;
  }

  public boolean isCreationActive() {
    return creationActive;
  }

  public boolean isExperienceActive() {
    return experienceActive;
  }

  public void setExperienceActive(boolean active) {
    experienceActive = active;
  }

  public void setCreationActive(boolean active) {
    creationActive = active;
  }
}