package net.sf.anathema.character.view;

import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;

public interface IConceptAndRulesViewFactory {

  ICharacterConceptAndRulesView createCharacterConceptView();
}