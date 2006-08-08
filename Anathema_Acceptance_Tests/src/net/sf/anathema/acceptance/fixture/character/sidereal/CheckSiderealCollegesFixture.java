package net.sf.anathema.acceptance.fixture.character.sidereal;

import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;
import net.sf.anathema.character.sidereal.colleges.model.CollegeType;
import net.sf.anathema.character.sidereal.colleges.model.SiderealCollegeModel;

public class CheckSiderealCollegesFixture extends AbstractCharacterColumnFixture {

  public String traitType;

  public boolean isCaste() {
    return getCollege().getFavorization().isCasteOrFavored();
  }

  private IFavorableModifiableTrait getCollege() {
    SiderealCollegeModel collegeModel = getSiderealCollegeModel();
    IFavorableModifiableTrait college = collegeModel.getCollege(CollegeType.valueOf(traitType));
    return college;
  }

  public int value() {
    return getCollege().getCurrentValue();
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