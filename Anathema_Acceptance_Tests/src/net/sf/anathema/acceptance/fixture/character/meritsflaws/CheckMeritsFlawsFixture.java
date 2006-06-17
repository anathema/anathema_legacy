package net.sf.anathema.acceptance.fixture.character.meritsflaws;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsAdditionalModel;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;

public class CheckMeritsFlawsFixture extends AbstractCharacterColumnFixture {

  public int meritsFlawsSelected() {
    return getModel().getSelectedQualities().length;
  }

  public int getCreationActiveMeritsFlaws() {
    int sum = 0;
    IQualitySelection[] selected = getModel().getSelectedQualities();
    for (IQualitySelection< ? extends IQuality> selection : selected) {
      if (selection.isCreationActive()) {
        sum++;
      }
    }
    return sum;
  }

  public int getExperienceActiveMeritsFlaws() {
    int sum = 0;
    IQualitySelection[] selected = getModel().getSelectedQualities();
    for (IQualitySelection< ? extends IQuality> selection : selected) {
      if (selection.isExperienceActive()) {
        sum++;
      }
    }
    return sum;
  }

  public int getBonusPointsSpent() {
    IAdditionalModelBonusPointCalculator bonusPointCalculator = getBonusPointCalculator();
    return bonusPointCalculator.getBonusPointCost();
  }

  public int getBonusPointsGranted() {
    IAdditionalModelBonusPointCalculator bonusPointCalculator = getBonusPointCalculator();
    return bonusPointCalculator.getBonusPointsGranted();
  }

  public int getCreationFlawPointsSelected() {
    int sum = 0;
    for (IQualitySelection< ? extends IQuality> selection : getModel().getSelectedFlaws()) {
      if (selection.isCreationActive()) {
        sum += selection.getPointValue();
      }
    }
    return sum;
  }

  public int getExperienceFlawPointsSelected() {
    int sum = 0;
    for (IQualitySelection< ? extends IQuality> selection : getModel().getSelectedFlaws()) {
      if (!selection.isCreationActive() && selection.isExperienceActive()) {
        sum += selection.getPointValue();
      }
    }
    return sum;
  }

  public int getExperiencePointsSpent() {
    return getExperienceCalculator().calculateCost();
  }

  public int getExperiencePointsGained() {
    return getExperienceCalculator().calculateGain();
  }

  private IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return getAdditionalModel().getExperienceCalculator();
  }

  private IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    IAdditionalModelBonusPointCalculator bonusPointCalculator = getAdditionalModel().getBonusPointCalculator();
    bonusPointCalculator.recalculate();
    return bonusPointCalculator;
  }

  public IMeritsFlawsModel getModel() {
    return getAdditionalModel().getMeritsFlawsModel();
  }

  public IMeritsFlawsAdditionalModel getAdditionalModel() {
    for (IAdditionalModel model : getCharacterStatistics().getExtendedConfiguration().getAdditionalModels()) {
      if (model instanceof IMeritsFlawsAdditionalModel) {
        return (IMeritsFlawsAdditionalModel) model;
      }
    }
    return null;
  }
}