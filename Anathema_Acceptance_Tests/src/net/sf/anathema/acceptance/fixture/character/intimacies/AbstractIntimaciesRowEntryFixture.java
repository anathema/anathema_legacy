package net.sf.anathema.acceptance.fixture.character.intimacies;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;

public abstract class AbstractIntimaciesRowEntryFixture extends AbstractCharacterRowEntryFixture {

  protected IIntimaciesModel getModel() {
    for (IAdditionalModel model : getCharacterStatistics().getExtendedConfiguration().getAdditionalModels()) {
      if (model instanceof IIntimaciesAdditionalModel) {
        return ((IIntimaciesAdditionalModel) model).getIntimaciesModel();
      }
    }
    return null;
  }
}