package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface EditionCharmGroups {
  ICharmGroup[] getCharmGroups(IExaltedEdition edition);
}
