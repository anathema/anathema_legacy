package net.sf.anathema.character.linguistics;

import net.sf.anathema.character.linguistics.presenter.ILinguisticsView;
import net.sf.anathema.character.linguistics.presenter.LinguisticsPresenter;
import net.sf.anathema.character.linguistics.template.LinguisticsTemplate;
import net.sf.anathema.character.main.hero.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(CharacterModelGroup.NaturalTraits)
@Weight(weight = 400)
public class LinguisticsInitializer implements CharacterModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public LinguisticsInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Linguistics");
    ILinguisticsView view = sectionView.addView(viewName, ILinguisticsView.class, character.getTemplate().getTemplateType().getCharacterType());
    ILinguisticsAdditionalModel linguisticsModel = (ILinguisticsAdditionalModel) character.getExtendedConfiguration().getAdditionalModel(LinguisticsTemplate.ID);
    new LinguisticsPresenter(linguisticsModel.getLinguisticsModel(), view, resources).initPresentation();
  }
}