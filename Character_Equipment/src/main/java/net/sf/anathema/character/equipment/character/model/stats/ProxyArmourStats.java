package net.sf.anathema.character.equipment.character.model.stats;

import com.google.common.base.Objects;
import net.sf.anathema.character.equipment.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.character.model.stats.modification.FatigueModification;
import net.sf.anathema.character.equipment.character.model.stats.modification.HardnessModification;
import net.sf.anathema.character.equipment.character.model.stats.modification.MobilityPenaltyModification;
import net.sf.anathema.character.equipment.character.model.stats.modification.SoakModification;
import net.sf.anathema.character.equipment.character.model.stats.modification.StatModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.StatsModification;
import net.sf.anathema.character.equipment.character.model.stats.modification.material.MaterialFatigueModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.material.MaterialHardnessModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.material.MaterialMobilityPenaltyModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.material.MaterialSoakModifier;
import net.sf.anathema.character.equipment.character.model.stats.modification.modifier.AttunementModifier;
import net.sf.anathema.character.main.equipment.weapon.IArmourStats;
import net.sf.anathema.character.main.util.IProxy;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.util.Identifier;

public class ProxyArmourStats extends AbstractStats implements IArmourStats, IProxy<IArmourStats> {

  private final IArmourStats delegate;
  private final BaseMaterial material;

  public ProxyArmourStats(IArmourStats stats, BaseMaterial material) {
    this.delegate = stats;
    this.material = material;
  }

  @Override
  public IArmourStats getUnderlying() {
    return this.delegate;
  }

  @Override
  public Integer getFatigue() {
    Integer fatigue = delegate.getFatigue();
    StatModifier modifier = createAttunementModifier(new MaterialFatigueModifier(material, fatigue));
    return getModifiedValue(new FatigueModification(modifier), fatigue);
  }

  @Override
  public Integer getHardness(HealthType type) {
    Integer hardness = delegate.getHardness(type);
    StatModifier modifier = createAttunementModifier(new MaterialHardnessModifier(material));
    return getModifiedValue(new HardnessModification(modifier), hardness);
  }

  @Override
  public Integer getMobilityPenalty() {
    Integer mobilityPenalty = delegate.getMobilityPenalty();
    StatModifier modifier = createAttunementModifier(new MaterialMobilityPenaltyModifier(material, mobilityPenalty));
    return getModifiedValue(new MobilityPenaltyModification(modifier), mobilityPenalty);
  }

  @Override
  public Integer getSoak(HealthType type) {
    Integer soak = delegate.getSoak(type);
    StatModifier modifier = createAttunementModifier(new MaterialSoakModifier(material, type));
    return getModifiedValue(new SoakModification(modifier), soak);
  }

  private AttunementModifier createAttunementModifier(StatModifier modifier) {
    return new AttunementModifier(modifier);
  }

  @Override
  public Identifier getName() {
    return delegate.getName();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ProxyArmourStats)) {
      return false;
    }
    ProxyArmourStats other = (ProxyArmourStats) obj;
    return Objects.equal(delegate, other.delegate) && Objects.equal(material, other.material);
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  @Override
  public String getId() {
    return getName().getId();
  }

  @Override
  public boolean representsItemForUseInCombat() {
    return delegate.representsItemForUseInCombat();
  }

  private Integer getModifiedValue(StatsModification modification, Integer original) {
    if (original == null) {
      return null;
    }
    return modification.getModifiedValue(original);
  }
}
