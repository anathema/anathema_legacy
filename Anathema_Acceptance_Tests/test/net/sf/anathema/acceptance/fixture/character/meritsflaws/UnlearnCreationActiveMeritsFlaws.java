package net.sf.anathema.acceptance.fixture.character.meritsflaws;

import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;

public class UnlearnCreationActiveMeritsFlaws extends AbstractMeritsFlawsCharacterRowEntryFixture {

  @Override
  public void enterRow() throws Exception {
    IMeritsFlawsModel model = getModel();
    IQualitySelection<IPerk> perkSelection = createPerkSelection(true);
    model.removeQualitySelection(perkSelection);
  }
}