package net.sf.anathema.character.library.quality.model;

import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualityPredicate;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;

public class QualityExclusion implements IQualityPredicate {

  private final IQuality prerequisite;

  public QualityExclusion(IQuality prerequisite) {
    this.prerequisite = prerequisite;
  }

  public boolean isFulfilled(IQualitySelection[] selectedQualities) {
    for (IQualitySelection selection : selectedQualities) {
      if (selection.getQuality() == prerequisite) {
        return false;
      }
    }
    return true;
  }
}