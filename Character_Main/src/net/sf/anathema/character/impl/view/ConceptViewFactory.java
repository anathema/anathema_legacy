package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.impl.view.concept.CharacterConceptAndRulesView;

public class ConceptViewFactory implements SubViewFactory {
  @Override
  public <T> T create() {
    return (T) new CharacterConceptAndRulesView();
  }
}