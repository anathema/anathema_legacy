package net.sf.anathema.acceptance.fixture.character.lunar.renown;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.lunar.renown.presenter.IRenownModel;

public class CheckFaceFixture extends AbstractCharacterColumnFixture {

  protected IRenownModel getModel() {
    for (IAdditionalModel model : getCharacterStatistics().getExtendedConfiguration().getAdditionalModels()) {
      if (model instanceof IRenownModel) {
        return (IRenownModel) model;
      }
    }
    return null;
  }

  public int getUltimateRank() {
    return getModel().getUltimateFaceRank();
  }

  public int getMaximumRankAllowed() {
    return getModel().getMaximumAllowedFaceRank();
  }
}
