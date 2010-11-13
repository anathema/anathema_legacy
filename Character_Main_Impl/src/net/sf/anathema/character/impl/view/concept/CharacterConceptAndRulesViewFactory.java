package net.sf.anathema.character.impl.view.concept;

import net.sf.anathema.character.view.ICharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;

public class CharacterConceptAndRulesViewFactory implements ICharacterConceptAndRulesViewFactory {

  public ICharacterConceptAndRulesView createCharacterConceptView() {
    return new CharacterConceptAndRulesView();
  }
}
