package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.view.concept.CharacterConceptAndRulesView;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;

@RegisteredCharacterView(ICharacterConceptAndRulesView.class)
public class ConceptViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new CharacterConceptAndRulesView();
  }
}