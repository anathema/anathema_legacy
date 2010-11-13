package net.sf.anathema.acceptance.fixture.character.meritsflaws;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.library.quality.model.QualitySelection;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.model.perk.PerkCategory;
import net.sf.anathema.character.meritsflaws.model.perk.PerkType;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsAdditionalModel;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;

public abstract class AbstractMeritsFlawsCharacterRowEntryFixture extends AbstractCharacterRowEntryFixture {

  public String type;
  public String category;
  public String id;
  public int value;

  protected IMeritsFlawsModel getModel() {
    for (IAdditionalModel model : getCharacterStatistics().getExtendedConfiguration().getAdditionalModels()) {
      if (model instanceof IMeritsFlawsAdditionalModel) {
        return ((IMeritsFlawsAdditionalModel) model).getMeritsFlawsModel();
      }
    }
    return null;
  }

  protected IQualitySelection<IPerk> createPerkSelection(boolean creationActive) {
    PerkType perkType = PerkType.valueOf(type);
    PerkCategory perkCategory = PerkCategory.valueOf(category);
    IMeritsFlawsModel model = getModel();

    IPerk perk = model.getPerk(id, perkType, perkCategory);
    IQualitySelection<IPerk> perkSelection = new QualitySelection<IPerk>(perk, value, creationActive);
    return perkSelection;
  }
}