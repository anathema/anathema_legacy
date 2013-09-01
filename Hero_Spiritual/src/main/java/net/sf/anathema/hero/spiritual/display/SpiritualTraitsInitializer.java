package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.framework.environment.dependencies.Weight;

import static net.sf.anathema.hero.display.HeroModelGroup.SpiritualTraits;

@RegisteredInitializer(SpiritualTraits)
@Weight(weight = 0)
public class SpiritualTraitsInitializer implements HeroModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public SpiritualTraitsInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String header = new DefaultSpiritualTraitsViewProperties(environment).getOverallHeader();
    SpiritualTraitsView view = sectionView.addView(header, SpiritualTraitsView.class);
    new BasicSpiritualTraitsPresenter(environment, hero, view).initPresentation();
  }
}
