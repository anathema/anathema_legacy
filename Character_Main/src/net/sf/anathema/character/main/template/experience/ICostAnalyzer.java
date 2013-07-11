package net.sf.anathema.character.main.template.experience;

import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import net.sf.anathema.character.main.magic.model.magic.Magic;

public interface ICostAnalyzer {

  MartialArtsLevel getMartialArtsLevel(Magic magic);

  boolean isMagicFavored(Magic magic);

}