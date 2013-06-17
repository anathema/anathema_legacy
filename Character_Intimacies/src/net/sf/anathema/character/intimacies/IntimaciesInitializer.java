package net.sf.anathema.character.intimacies;

import net.sf.anathema.character.intimacies.presenter.IIntimaciesView;
import net.sf.anathema.character.intimacies.presenter.IntimaciesPresenter;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.main.hero.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(CharacterModelGroup.SpiritualTraits)
@Weight(weight = 300)
public class IntimaciesInitializer implements CharacterModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public IntimaciesInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, final Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Intimacies");
    IIntimaciesView view = sectionView.addView(viewName, IIntimaciesView.class, character.getCharacterType());
    IIntimaciesAdditionalModel additionalModel = (IIntimaciesAdditionalModel) character.getExtendedConfiguration().getAdditionalModel(IntimaciesTemplate.ID);
    new IntimaciesPresenter(additionalModel.getIntimaciesModel(), additionalModel, view, resources).initPresentation();
  }

}