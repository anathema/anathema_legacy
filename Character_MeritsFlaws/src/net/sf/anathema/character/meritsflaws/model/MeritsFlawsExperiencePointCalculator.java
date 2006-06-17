package net.sf.anathema.character.meritsflaws.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;

public class MeritsFlawsExperiencePointCalculator implements IAdditionalModelExperienceCalculator {

  private final IMeritsFlawsModel model;

  public MeritsFlawsExperiencePointCalculator(IMeritsFlawsModel model) {
    this.model = model;
  }

  public int calculateCost() {
    int experienceSum = 0;
    for (IQualitySelection< ? extends IQuality> selection : model.getSelectedMerits()) {
      if (!selection.isCreationActive() && selection.isExperienceActive()) {
        experienceSum += selection.getPointValue();
      }
    }
    for (IQualitySelection< ? extends IQuality> selection : model.getSelectedFlaws()) {
      if (selection.isCreationActive() && !selection.isExperienceActive()) {
        experienceSum += selection.getPointValue();
      }
    }
    return experienceSum * 2;
  }

  public int calculateGain() {
    int experienceSum = 0;
    int activeFlawSum = 0;
    for (IQualitySelection< ? extends IQuality> selection : model.getSelectedFlaws()) {
      if (selection.isCreationActive() && selection.isExperienceActive()) {
        activeFlawSum += selection.getPointValue();
      }
    }
    for (IQualitySelection< ? extends IQuality> selection : model.getSelectedFlaws()) {
      if (!selection.isCreationActive() && selection.isExperienceActive()) {
        int pointValue = selection.getPointValue();
        experienceSum += Math.min(10 - activeFlawSum, pointValue);
        if (activeFlawSum + pointValue <= 10) {
          activeFlawSum += pointValue;
        }
        else {
          activeFlawSum = 10;
        }
      }
    }
    experienceSum = Math.min(10, experienceSum);
    for (IQualitySelection< ? extends IQuality> selection : model.getSelectedMerits()) {
      if (selection.isCreationActive() && !selection.isExperienceActive()) {
        experienceSum += selection.getPointValue();
      }
    }
    return experienceSum * 2;
  }
}