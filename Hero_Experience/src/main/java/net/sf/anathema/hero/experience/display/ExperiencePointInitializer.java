package net.sf.anathema.hero.experience.display;

import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.framework.environment.Resources;

@RegisteredInitializer(HeroModelGroup.Miscellaneous)
public class ExperiencePointInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public ExperiencePointInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    new ExperiencePointPresenter(resources, hero).initPresentation(sectionView);
  }
}