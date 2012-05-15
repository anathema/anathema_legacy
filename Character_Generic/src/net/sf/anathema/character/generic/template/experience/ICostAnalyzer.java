package net.sf.anathema.character.generic.template.experience;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;

public interface ICostAnalyzer {

  boolean isOccultFavored();

  MartialArtsLevel getMartialArtsLevel(ICharm charm);

  boolean isMagicFavored(IMagic magic);
}