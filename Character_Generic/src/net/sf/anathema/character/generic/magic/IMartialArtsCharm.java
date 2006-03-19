package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;

public interface IMartialArtsCharm extends ICharm {
  
  public MartialArtsLevel getLevel();
  
  public boolean isFormCharm();
}