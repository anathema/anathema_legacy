package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.lib.util.Identifier;

public interface CharmCache extends ExtensibleDataSet, CharmIdMap{

  Identifier[] getCharmTypes();

  ISpecialCharm[] getSpecialCharmData(Identifier type);

  Charm[] getCharms(Identifier type);

  CharmProvider getCharmProvider();
}