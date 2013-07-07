package net.sf.anathema.character.main.template.experience;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;

public interface ICostAnalyzer {

  boolean isOccultFavored();

  MartialArtsLevel getMartialArtsLevel(ICharm charm);

  boolean isMagicFavored(IMagic magic);
}