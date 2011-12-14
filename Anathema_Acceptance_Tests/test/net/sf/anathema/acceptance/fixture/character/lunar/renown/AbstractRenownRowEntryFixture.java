package net.sf.anathema.acceptance.fixture.character.lunar.renown;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.lunar.renown.presenter.IRenownModel;

public abstract class AbstractRenownRowEntryFixture extends AbstractCharacterRowEntryFixture {
  protected IRenownModel getModel() {
    for (IAdditionalModel model : getCharacterStatistics().getExtendedConfiguration().getAdditionalModels()) {
      if (model instanceof IRenownModel) {
        return (IRenownModel) model;
      }
    }
    return null;
  }
}
