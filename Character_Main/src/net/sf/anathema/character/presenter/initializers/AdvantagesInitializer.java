package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.BasicAdvantagePresenter;
import net.sf.anathema.character.presenter.DefaultAdvantageViewProperties;
import net.sf.anathema.character.view.AdvantageView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.SpiritualTraits;

@RegisteredInitializer(SpiritualTraits)
@Weight(weight = 0)
public class AdvantagesInitializer implements CharacterModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public AdvantagesInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String header = new DefaultAdvantageViewProperties(resources).getOverallHeader();
    AdvantageView view = sectionView.addView(header, AdvantageView.class, character.getTemplate().getTemplateType().getCharacterType());
    new BasicAdvantagePresenter(resources, character, view).initPresentation();
  }
}
