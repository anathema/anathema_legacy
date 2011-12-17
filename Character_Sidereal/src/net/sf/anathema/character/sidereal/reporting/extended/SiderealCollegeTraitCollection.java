package net.sf.anathema.character.sidereal.reporting.extended;

import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeModel;

public class SiderealCollegeTraitCollection extends AbstractTraitCollection {

  public SiderealCollegeTraitCollection(ISiderealCollegeModel collegeModel) {
    for (IAstrologicalHouse house : collegeModel.getAllHouses()) {
      addTraits(house.getColleges());
    }
  }
}