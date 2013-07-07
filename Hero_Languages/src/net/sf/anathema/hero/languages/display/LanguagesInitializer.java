package net.sf.anathema.hero.languages.display;

import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.model.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.languages.model.LanguagesModel;
import net.sf.anathema.hero.languages.model.LanguagesModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(HeroModelGroup.NaturalTraits)
@Weight(weight = 400)
public class LanguagesInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public LanguagesInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Linguistics");
    LanguagesView view = sectionView.addView(viewName, LanguagesView.class, hero.getTemplate().getTemplateType().getCharacterType());
    LanguagesModel languagesModel = LanguagesModelFetcher.fetch(hero);
    new LanguagesPresenter(languagesModel, view, resources).initPresentation();
  }
}