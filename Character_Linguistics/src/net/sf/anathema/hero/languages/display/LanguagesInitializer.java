package net.sf.anathema.hero.languages.display;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.languages.model.LanguagesModel;
import net.sf.anathema.hero.languages.model.LanguagesModelFetcher;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(HeroModelGroup.NaturalTraits)
@Weight(weight = 400)
public class LanguagesInitializer implements CharacterModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public LanguagesInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Linguistics");
    LanguagesView view = sectionView.addView(viewName, LanguagesView.class, character.getTemplate().getTemplateType().getCharacterType());
    LanguagesModel languagesModel = LanguagesModelFetcher.fetch(character);
    new LanguagesPresenter(languagesModel, view, resources).initPresentation();
  }
}