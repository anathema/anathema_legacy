package net.sf.anathema.character.platform.specialties;

import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesAdditionalModel;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesConfigurationView;
import net.sf.anathema.character.presenter.specialty.SpecialtiesConfigurationPresenter;
import net.sf.anathema.character.presenter.specialty.SpecialtiesTemplate;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(HeroModelGroup.NaturalTraits)
@Weight(weight = 300)
public class SpecialtiesInitializer implements CharacterModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public SpecialtiesInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Specialties");
    ISpecialtiesConfigurationView view = sectionView.addView(viewName, ISpecialtiesConfigurationView.class, character.getTemplate().getTemplateType().getCharacterType());
    ISpecialtiesAdditionalModel specialtiesAdditionalModel = (ISpecialtiesAdditionalModel) character.getExtendedConfiguration().getAdditionalModel(SpecialtiesTemplate.ID);
    ISpecialtiesConfiguration specialtiesModel = specialtiesAdditionalModel.getSpecialtiesModel();
    new SpecialtiesConfigurationPresenter(character, specialtiesModel, view, resources).initPresentation();
  }
}