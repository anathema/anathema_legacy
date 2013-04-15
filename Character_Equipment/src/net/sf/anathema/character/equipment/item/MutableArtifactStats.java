package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.lib.util.Identified;

public interface MutableArtifactStats extends IArtifactStats {
  void setName(Identified name);

  void setAttuneCost(int cost);

  void setAllowForeignAttunement(boolean value);

  void setRequireAttunement(boolean value);
}
