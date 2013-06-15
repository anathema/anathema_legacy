package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.CharacterConceptAndRulesPresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.model.CharacterModelGroup.Outline;

@RegisteredInitializer(Outline)
public class ConceptInitializer implements CoreModelInitializer {

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String conceptHeader = resources.getString("CardView.CharacterConcept.Title");
    ICharacterType characterType = character.getCharacterType();
    ICharacterConceptAndRulesView conceptView = sectionView.addView(conceptHeader, ICharacterConceptAndRulesView.class, characterType);
    new CharacterConceptAndRulesPresenter(character, conceptView, resources).initPresentation();
  }
}