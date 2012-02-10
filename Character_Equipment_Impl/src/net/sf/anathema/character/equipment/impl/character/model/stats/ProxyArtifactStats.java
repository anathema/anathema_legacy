package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.util.IProxy;
import net.sf.anathema.lib.util.IIdentificate;

public class ProxyArtifactStats extends AbstractStats implements IArtifactStats, IProxy<IArtifactStats> 
{
	  private final IArtifactStats delegate;
	  private final MagicalMaterial material;
	  private final IExaltedRuleSet ruleSet;

	  public ProxyArtifactStats(IArtifactStats stats, MagicalMaterial material, IExaltedRuleSet ruleSet) {
	    this.delegate = stats;
	    this.material = material;
	    this.ruleSet = ruleSet;
	  }
	  
	  public IArtifactStats getUnderlying() {
	    return this.delegate;
	  }

	  public Integer getAttuneCost() {
	    return delegate.getAttuneCost();
	  }
	  
	  public IIdentificate getName() {
		    return delegate.getName();
		  }
	  
		public ArtifactAttuneType getAttuneType()
		{
			return delegate.getAttuneType();
		}
		
	  public boolean allowForeignAttunement()
	  {
		  return delegate.allowForeignAttunement();
	  }
	  
	  public boolean requireAttunementToUse()
	  {
		  return delegate.requireAttunementToUse();
	  }

	  @Override
	  public boolean equals(Object obj) {
	    if (obj == null || obj.getClass() != getClass()) {
	      return false;
	    }
	    ProxyArtifactStats other = (ProxyArtifactStats) obj;
	    return ObjectUtilities.equals(delegate, other.delegate)
	        && ObjectUtilities.equals(material, other.material)
	        && ObjectUtilities.equals(ruleSet, other.ruleSet);
	  }

	  @Override
	  public int hashCode() {
	    return ObjectUtilities.getHashCode(delegate, material, ruleSet);
	  }
	  
	  @Override
	  public IEquipmentStats[] getViews() {
	    return new IEquipmentStats[] { this };
	  }

	  @Override
	  public String getId() {
	    return delegate.getId();
	  }
	  
	  @Override
	  public Object[] getApplicableMaterials()
	  {
		  return delegate.getApplicableMaterials();
	  }
}
