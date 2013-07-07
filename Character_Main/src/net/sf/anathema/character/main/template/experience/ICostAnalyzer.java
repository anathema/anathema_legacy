package net.sf.anathema.character.main.template.experience;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.IMagic;
import net.sf.anathema.character.main.magic.charms.MartialArtsLevel;

public interface ICostAnalyzer {

  boolean isOccultFavored();

  MartialArtsLevel getMartialArtsLevel(ICharm charm);

  boolean isMagicFavored(IMagic magic);
}