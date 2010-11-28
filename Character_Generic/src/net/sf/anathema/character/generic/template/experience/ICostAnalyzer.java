package net.sf.anathema.character.generic.template.experience;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;

public interface ICostAnalyzer {

  public boolean isOccultFavored();

  public MartialArtsLevel getMartialArtsLevel(ICharm charm);

  public boolean isMagicFavored(IMagic magic);
}