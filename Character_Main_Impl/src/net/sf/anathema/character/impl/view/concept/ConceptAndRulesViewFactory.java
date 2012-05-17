package net.sf.anathema.character.impl.view.concept;

import net.sf.anathema.character.view.IConceptAndRulesViewFactory;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;

public class ConceptAndRulesViewFactory implements IConceptAndRulesViewFactory {

  @Override
  public ICharacterConceptAndRulesView createCharacterConceptView() {
    return new CharacterConceptAndRulesView();
  }
}
