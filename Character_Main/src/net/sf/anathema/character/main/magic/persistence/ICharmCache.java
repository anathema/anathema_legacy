package net.sf.anathema.character.main.magic.persistence;

import net.sf.anathema.character.main.framework.data.IExtensibleDataSet;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.lib.util.Identifier;

public interface ICharmCache extends IExtensibleDataSet {
  ICharm[] getCharms(Identifier type);

  Identifier[] getCharmTypes();

  ISpecialCharm[] getSpecialCharmData(Identifier type);

  String getCharmRename(String name);
}