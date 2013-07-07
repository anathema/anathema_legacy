package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(HeroModelGroup.NaturalTraits)
@Weight(weight = 300)
public class SpecialtiesInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public SpecialtiesInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Specialties");
    ICharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
    SpecialtiesConfigurationView view = sectionView.addView(viewName, SpecialtiesConfigurationView.class, characterType);
    SpecialtiesModel specialtiesModel = SpecialtiesModelFetcher.fetch(hero);
    new SpecialtiesConfigurationPresenter(hero, specialtiesModel, view, resources).initPresentation();
  }
}