package net.sf.anathema.acceptance.fixture.character.sidereal;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;
import net.sf.anathema.character.sidereal.colleges.model.CollegeType;
import net.sf.anathema.character.sidereal.colleges.model.SiderealCollegeModel;

public class SetSiderealCollegesFixture extends AbstractCharacterRowEntryFixture {

  public String traitType;
  public int value;

  @Override
  public void enterRow() throws Exception {
    SiderealCollegeModel collegeModel = getSiderealCollegeModel();
    IFavorableModifiableTrait college = collegeModel.getCollege(CollegeType.valueOf(traitType));
    college.setCurrentValue(value);
  }

  private SiderealCollegeModel getSiderealCollegeModel() {
    for (IAdditionalModel model : getCharacterStatistics().getExtendedConfiguration().getAdditionalModels()) {
      if (model instanceof SiderealCollegeModel) {
        return (SiderealCollegeModel) model;
      }
    }
    throw new IllegalStateException("No Sidereal College Model found."); //$NON-NLS-1$
  }
}