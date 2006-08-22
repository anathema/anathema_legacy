package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.library.trait.specialties.ISpecialtyConfiguration;

public interface ISpecialtiesAdditionalModel extends IAdditionalModel {

  public ISpecialtyConfiguration getSpecialtiesModel();
}