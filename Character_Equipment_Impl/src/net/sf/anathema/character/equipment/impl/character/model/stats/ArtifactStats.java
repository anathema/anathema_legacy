package net.sf.anathema.character.equipment.impl.character.model.stats;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

import java.util.List;

public class ArtifactStats extends AbstractNonCombatStats implements IArtifactStats {
  int attuneCost;
  boolean allowForeignAttunement;
  boolean requireAttunement;

  @Override
  public Integer getAttuneCost() {
    return attuneCost;
  }

  public void setAttuneCost(int cost) {
    attuneCost = cost;
  }

  @Override
  public ArtifactAttuneType getAttuneType() {
    return ArtifactAttuneType.FullyAttuned;
  }

  @Override
  public boolean allowForeignAttunement() {
    return allowForeignAttunement;
  }

  @Override
  public boolean requireAttunementToUse() {
    return requireAttunement;
  }

  public void setAllowForeignAttunement(boolean value) {
    allowForeignAttunement = value;
  }

  public void setRequireAttunement(boolean value) {
    requireAttunement = value;
  }

  @Override
  public IEquipmentStats[] getViews() {
    List<IEquipmentStats> views = Lists.newArrayList();
    if (allowForeignAttunement()) {
      views.add(createAttunement(ArtifactAttuneType.PartiallyAttuned));
      views.add(createAttunement(ArtifactAttuneType.ExpensivePartiallyAttuned));
      views.add(createAttunement(ArtifactAttuneType.FullyAttuned));
      views.add(createAttunement(ArtifactAttuneType.UnharmoniouslyAttuned));
    } else {
      views.add(createAttunement(ArtifactAttuneType.FullyAttuned));
    }
    return views.toArray(new IEquipmentStats[views.size()]);
  }

  private ArtifactStatsDecorator createAttunement(ArtifactAttuneType artifactAttuneType) {
    return new ArtifactStatsDecorator(this, artifactAttuneType, requireAttunement);
  }

  @Override
  public String getId() {
    return getName().getId();
  }

  public String toString() {
    return getId() + "[" + attuneCost + "m]";
  }
}