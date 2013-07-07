package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.IntimaciesModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(HeroModelGroup.SpiritualTraits)
@Weight(weight = 300)
public class IntimaciesInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public IntimaciesInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, final Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Intimacies");
    ICharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
    IntimaciesView view = sectionView.addView(viewName, IntimaciesView.class, characterType);
    IntimaciesModel intimaciesModel = IntimaciesModelFetcher.fetch(hero);
    new IntimaciesPresenter(intimaciesModel, view, resources).initPresentation();
  }

}