package net.sf.anathema.character.framework.library.quality.model;

import net.sf.anathema.character.framework.library.quality.presenter.IQuality;
import net.sf.anathema.character.framework.library.quality.presenter.IQualityPredicate;
import net.sf.anathema.character.framework.library.quality.presenter.IQualitySelection;

public class QualityPrerequisite implements IQualityPredicate {

  private final IQuality[] prerequisites;

  public QualityPrerequisite(IQuality[] prerequisites) {
    this.prerequisites = prerequisites;
  }

  public QualityPrerequisite(IQuality prerequisite) {
    this(new IQuality[]{prerequisite});
  }

  @Override
  public boolean isFulfilled(IQualitySelection<? extends IQuality>[] selectedQualities) {
    for (IQuality prerequisite : prerequisites) {
      for (IQualitySelection<? extends IQuality> selection : selectedQualities) {
        if (selection.getQuality() == prerequisite) {
          return true;
        }
      }
    }
    return false;
  }

  public IQuality[] getPrerequisiteQualities() {
    return prerequisites;
  }
}