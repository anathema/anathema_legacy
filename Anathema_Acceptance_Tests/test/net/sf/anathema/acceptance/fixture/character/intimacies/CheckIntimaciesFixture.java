package net.sf.anathema.acceptance.fixture.character.intimacies;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;

public class CheckIntimaciesFixture extends AbstractCharacterColumnFixture {

  public int intimaciesSelected() {
    return getModel().getEntries().size();
  }

  private IIntimaciesModel getModel() {
    return getAdditionalModel().getIntimaciesModel();
  }

  private IIntimaciesAdditionalModel getAdditionalModel() {
    for (IAdditionalModel model : getCharacterStatistics().getExtendedConfiguration().getAdditionalModels()) {
      if (model instanceof IIntimaciesAdditionalModel) {
        return (IIntimaciesAdditionalModel) model;
      }
    }
    return null;
  }

  public int getBonusPointsSpent() {
    IAdditionalModelBonusPointCalculator bonusPointCalculator = getBonusPointCalculator();
    return bonusPointCalculator.getBonusPointCost();
  }

  private IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    IAdditionalModelBonusPointCalculator bonusPointCalculator = getAdditionalModel().getBonusPointCalculator();
    bonusPointCalculator.recalculate();
    return bonusPointCalculator;
  }

  public int getFreeIntimacies() {
    return getModel().getFreeIntimacies();
  }

  public int getMaximumIntimacies() {
    return getModel().getIntimaciesLimit();
  }
}
