package net.sf.anathema.character.main.magic.charmtree.cache;

import net.sf.anathema.character.main.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.lib.util.Identifier;

public interface CharmCache extends ExtensibleDataSet {

  Charm getCharm(String charmId);

  Identifier[] getCharmTypes();

  ISpecialCharm[] getSpecialCharmData(Identifier type);

  Charm[] getCharms(Identifier type);

  CharmProvider getCharmProvider();
}