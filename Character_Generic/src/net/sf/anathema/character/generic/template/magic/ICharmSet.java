package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ICharmSet {

  ICharm[] getCharms();

  ICharm[] getUniqueCharms();

  ICharm[] getMartialArtsCharms();
}