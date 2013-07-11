package net.sf.anathema.character.main.template.experience;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import net.sf.anathema.character.main.magic.model.magic.Magic;

public interface ICostAnalyzer {

  MartialArtsLevel getMartialArtsLevel(Charm charm);

  boolean isMagicFavored(Magic magic);
}