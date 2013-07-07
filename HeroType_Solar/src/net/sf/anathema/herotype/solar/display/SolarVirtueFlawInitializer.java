package net.sf.anathema.herotype.solar.display;

import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.herotype.solar.model.DescriptiveVirtueFlawModel;
import net.sf.anathema.herotype.solar.model.GreatCurseFetcher;
import net.sf.anathema.herotype.solar.model.SolarCharacterType;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(HeroModelGroup.SpiritualTraits)
@Weight(weight = 200)
public class SolarVirtueFlawInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public SolarVirtueFlawInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    if (!(hero.getTemplate().getTemplateType().getCharacterType() instanceof SolarCharacterType)) {
      return;
    }
    String viewName = resources.getString("AdditionalTemplateView.TabName.SolarVirtueFlaw");
    IDescriptiveVirtueFlawView view = sectionView.addView(viewName, IDescriptiveVirtueFlawView.class, hero.getTemplate().getTemplateType().getCharacterType());
    DescriptiveVirtueFlawModel virtueFlawModel = (DescriptiveVirtueFlawModel) GreatCurseFetcher.fetch(hero);
    SolarVirtueFlawPresenter presenter = new SolarVirtueFlawPresenter(hero,resources, view, virtueFlawModel);
    presenter.initPresentation();
  }
}