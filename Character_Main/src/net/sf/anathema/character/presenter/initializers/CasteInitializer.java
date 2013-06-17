package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.CastePresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.concept.CasteView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.main.model.CharacterModelGroup.Outline;

@RegisteredInitializer(Outline)
@Weight(weight = 100)
public class CasteInitializer implements CharacterModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public CasteInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    if (character.getHeroTemplate().getCasteCollection().isEmpty()){
      return;
    }
    String conceptHeader = resources.getString("CardView.CharacterConcept.Title");
    ICharacterType characterType = character.getCharacterType();
    CasteView conceptView = sectionView.addView(conceptHeader, CasteView.class, characterType);
    new CastePresenter(character, conceptView, resources).initPresentation();
  }
}