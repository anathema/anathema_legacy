package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.main.equipment.ArtifactStats;
import net.sf.anathema.lib.util.Identifier;

public interface MutableArtifactStats extends ArtifactStats {
  void setName(Identifier name);

  void setAttuneCost(int cost);

  void setAllowForeignAttunement(boolean value);

  void setRequireAttunement(boolean value);
}