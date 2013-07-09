package net.sf.anathema.character.main.template.experience;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;

public interface ICostAnalyzer {

  boolean isOccultFavored();

  MartialArtsLevel getMartialArtsLevel(Charm charm);

  boolean isMagicFavored(Magic magic);
}