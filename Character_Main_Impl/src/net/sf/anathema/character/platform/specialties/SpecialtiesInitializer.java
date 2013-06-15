package net.sf.anathema.character.platform.specialties;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesAdditionalModel;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesConfigurationView;
import net.sf.anathema.character.presenter.specialty.SpecialtiesConfigurationPresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

public class SpecialtiesInitializer implements IAdditionalInitializer {

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources, IAdditionalModel model) {
    String viewName = resources.getString("AdditionalTemplateView.TabName." + model.getTemplateId());
    ISpecialtiesConfigurationView view = sectionView.addView(viewName, ISpecialtiesConfigurationView.class, character.getCharacterType());
    ISpecialtiesConfiguration specialtiesModel = ((ISpecialtiesAdditionalModel) model).getSpecialtiesModel();
    new SpecialtiesConfigurationPresenter(specialtiesModel, view, resources).initPresentation();
  }
}