package net.sf.anathema.hero.equipment.sheet.content.stats;

import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

public interface ArtifactStats extends IEquipmentStats {
  Integer getAttuneCost();

  boolean allowForeignAttunement();

  boolean requireAttunementToUse();

  ArtifactAttuneType getAttuneType();

  IEquipmentStats[] getViews();
}