package net.sf.anathema.hero.othertraits.display;

import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.SpiritualTraits;

@RegisteredInitializer(SpiritualTraits)
@Weight(weight = 0)
public class AdvantagesInitializer implements HeroModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public AdvantagesInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    String header = new DefaultAdvantageViewProperties(resources).getOverallHeader();
    AdvantageView view = sectionView.addView(header, AdvantageView.class, hero.getTemplate().getTemplateType().getCharacterType());
    new BasicAdvantagePresenter(resources, hero, view).initPresentation();
  }
}
