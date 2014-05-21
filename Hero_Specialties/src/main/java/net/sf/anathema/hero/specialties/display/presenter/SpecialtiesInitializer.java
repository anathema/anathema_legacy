package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.hero.specialties.SpecialtiesModel;
import net.sf.anathema.hero.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.character.framework.display.SectionView;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.framework.environment.dependencies.Weight;

@RegisteredInitializer(HeroModelGroup.NaturalTraits)
@Weight(weight = 300)
public class SpecialtiesInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public SpecialtiesInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String viewName = environment.getString("AdditionalTemplateView.TabName.Specialties");
    SpecialtiesConfigurationView view = sectionView.addView(viewName, SpecialtiesConfigurationView.class);
    SpecialtiesModel specialtiesModel = SpecialtiesModelFetcher.fetch(hero);
    new SpecialtiesConfigurationPresenter(hero, specialtiesModel, view, environment).initPresentation();
  }
}