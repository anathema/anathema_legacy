package net.sf.anathema.herotype.solar.display.curse;

import net.sf.anathema.character.framework.display.SectionView;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.herotype.solar.model.SolarCharacterType;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveVirtueFlawModel;
import net.sf.anathema.herotype.solar.model.curse.GreatCurseFetcher;
import net.sf.anathema.framework.environment.dependencies.Weight;

@RegisteredInitializer(HeroModelGroup.SpiritualTraits)
@Weight(weight = 200)
public class SolarVirtueFlawInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public SolarVirtueFlawInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    if (!(hero.getTemplate().getTemplateType().getCharacterType() instanceof SolarCharacterType)) {
      return;
    }
    String viewName = environment.getString("AdditionalTemplateView.TabName.SolarVirtueFlaw");
    ConfigurableCharacterView view = sectionView.addView(viewName, ConfigurableCharacterView.class);
    DescriptiveVirtueFlawModel virtueFlawModel = (DescriptiveVirtueFlawModel) GreatCurseFetcher.fetch(hero);
    SolarVirtueFlawPresenter presenter = new SolarVirtueFlawPresenter(hero, environment, view, virtueFlawModel);
    presenter.initPresentation();
  }
}