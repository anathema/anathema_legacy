package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.IntimaciesModelFetcher;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(HeroModelGroup.SpiritualTraits)
@Weight(weight = 300)
public class IntimaciesInitializer implements CharacterModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public IntimaciesInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, final Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Intimacies");
    IntimaciesView view = sectionView.addView(viewName, IntimaciesView.class, character.getTemplate().getTemplateType().getCharacterType());
    IntimaciesModel intimaciesModel = IntimaciesModelFetcher.fetch(character);
    new IntimaciesPresenter(intimaciesModel, view, resources).initPresentation();
  }

}