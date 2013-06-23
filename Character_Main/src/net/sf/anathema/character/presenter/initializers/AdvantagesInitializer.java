package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.presenter.BasicAdvantagePresenter;
import net.sf.anathema.character.presenter.DefaultAdvantageViewProperties;
import net.sf.anathema.character.view.AdvantageView;
import net.sf.anathema.character.view.SectionView;
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
