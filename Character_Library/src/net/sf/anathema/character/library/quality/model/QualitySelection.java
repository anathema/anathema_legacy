package net.sf.anathema.character.library.quality.model;

import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;

public class QualitySelection<Q extends IQuality> implements IQualitySelection<Q> {

  private final Q perk;
  private final int pointValue;
  private boolean creationActive;
  private boolean experienceActive;

  public QualitySelection(Q perk, int pointValue, boolean creationActive) {
    this(perk, pointValue, creationActive, true);
  }

  public QualitySelection(Q perk, int value, boolean creationActive, boolean experienceActive) {
    this.perk = perk;
    this.pointValue = value;
    this.creationActive = creationActive;
    this.experienceActive = experienceActive;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof QualitySelection)) {
      return false;
    }
    QualitySelection other = (QualitySelection) obj;
    return other.perk == perk
        && other.pointValue == pointValue
        && other.creationActive == creationActive
        && other.experienceActive == experienceActive;
  }

  @Override
  public int hashCode() {
    return perk.hashCode() + pointValue * 11;
  }

  public Q getQuality() {
    return perk;
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