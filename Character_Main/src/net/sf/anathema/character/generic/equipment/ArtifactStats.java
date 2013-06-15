package net.sf.anathema.character.generic.equipment;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public interface ArtifactStats extends IEquipmentStats {
  Integer getAttuneCost();

  boolean allowForeignAttunement();

  boolean requireAttunementToUse();

  ArtifactAttuneType getAttuneType();

  IEquipmentStats[] getViews();
}