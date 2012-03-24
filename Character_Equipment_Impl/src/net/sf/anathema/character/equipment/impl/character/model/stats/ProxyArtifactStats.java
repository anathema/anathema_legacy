package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.util.IProxy;
import net.sf.anathema.lib.util.IIdentificate;

public class ProxyArtifactStats extends AbstractStats implements IArtifactStats, IProxy<IArtifactStats> {
  private final IArtifactStats delegate;
  private final MagicalMaterial material;

  public ProxyArtifactStats(IArtifactStats stats, MagicalMaterial material) {
    this.delegate = stats;
    this.material = material;
  }

  @Override
  public IArtifactStats getUnderlying() {
    return this.delegate;
  }

  @Override
  public Integer getAttuneCost() {
    return delegate.getAttuneCost();
  }

  @Override
  public IIdentificate getName() {
    return delegate.getName();
  }

  @Override
  public ArtifactAttuneType getAttuneType() {
    return delegate.getAttuneType();
  }

  @Override
  public boolean allowForeignAttunement() {
    return delegate.allowForeignAttunement();
  }

  @Override
  public boolean requireAttunementToUse() {
    return delegate.requireAttunementToUse();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }
    ProxyArtifactStats other = (ProxyArtifactStats) obj;
    return ObjectUtilities.equals(delegate, other.delegate) && ObjectUtilities.equals(material, other.material);
  }

  @Override
  public int hashCode() {
    return ObjectUtilities.getHashCode(delegate, material);
  }

  @Override
  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[]{this};
  }

  @Override
  public String getId() {
    return delegate.getId();
  }

  @Override
  public Object[] getApplicableMaterials() {
    return delegate.getApplicableMaterials();
  }

  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean representsItemForUseInCombat() {
    return delegate.representsItemForUseInCombat();
  }
}
