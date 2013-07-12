package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.lib.util.Identifier;

public interface CharmCache extends ExtensibleDataSet {

  Charm[] getCharms(Identifier type);

  Identifier[] getCharmTypes();

  ISpecialCharm[] getSpecialCharmData(Identifier type);
}