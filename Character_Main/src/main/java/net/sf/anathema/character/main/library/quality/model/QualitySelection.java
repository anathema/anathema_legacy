package net.sf.anathema.character.main.library.quality.model;

import net.sf.anathema.character.main.library.quality.presenter.IQuality;
import net.sf.anathema.character.main.library.quality.presenter.IQualitySelection;

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
    if (!(obj instanceof QualitySelection<?>)) {
      return false;
    }
    QualitySelection<?> other = (QualitySelection<?>) obj;
    return other.quality == quality && other.pointValue == pointValue && other.creationActive == creationActive &&
           other.experienceActive == experienceActive;
  }

  @Override
  public int hashCode() {
    return quality.hashCode() + pointValue * 11;
  }

  @Override
  public Q getQuality() {
    return quality;
  }

  @Override
  public int getPointValue() {
    return pointValue;
  }

  @Override
  public boolean isCreationActive() {
    return creationActive;
  }

  @Override
  public boolean isExperienceActive() {
    return experienceActive;
  }

  @Override
  public void setExperienceActive(boolean active) {
    experienceActive = active;
  }

  @Override
  public void setCreationActive(boolean active) {
    creationActive = active;
  }
}