package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.BasicAdvantagePresenter;
import net.sf.anathema.character.presenter.BasicAdvantageViewProperties;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.model.CharacterModelGroup.SpiritualTraits;

@RegisteredInitializer(SpiritualTraits)
public class AdvantagesInitializer implements CoreModelInitializer {
  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String header = new BasicAdvantageViewProperties(resources).getOverallHeader();
    IBasicAdvantageView view = sectionView.addView(header, IBasicAdvantageView.class, character.getCharacterType());
    new BasicAdvantagePresenter(resources, character, view).initPresentation();
  }
}
