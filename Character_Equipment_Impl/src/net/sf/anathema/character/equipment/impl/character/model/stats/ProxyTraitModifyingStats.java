package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.util.IProxy;
import net.sf.anathema.lib.util.IIdentificate;

public class ProxyTraitModifyingStats extends AbstractStats implements ITraitModifyingStats, IProxy<ITraitModifyingStats> 
{
	  private final ITraitModifyingStats delegate;
	  private final MagicalMaterial material;
	  private final IExaltedRuleSet ruleSet;

	  public ProxyTraitModifyingStats(ITraitModifyingStats stats, MagicalMaterial material, IExaltedRuleSet ruleSet) {
	    this.delegate = stats;
	    this.material = material;
	    this.ruleSet = ruleSet;
	  }
	  
	  public ITraitModifyingStats getUnderlying() {
	    return this.delegate;
	  }

	  public IIdentificate getName() {
		    return delegate.getName();
		  }

	  @Override
	  public boolean equals(Object obj) {
	    if (obj == null || obj.getClass() != getClass()) {
	      return false;
	    }
	    ProxyTraitModifyingStats other = (ProxyTraitModifyingStats) obj;
	    return ObjectUtilities.equals(delegate, other.delegate)
	        && ObjectUtilities.equals(material, other.material)
	        && ObjectUtilities.equals(ruleSet, other.ruleSet);
	  }

	  @Override
	  public int hashCode() {
	    return ObjectUtilities.getHashCode(delegate, material, ruleSet);
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

	@Override
	public Integer getDDVMod() {
		return delegate.getDDVMod();
	}

	@Override
	public Integer getJoinBattleMod() {
		return delegate.getJoinBattleMod();
	}

	@Override
	public Integer getJoinDebateMod() {
		return delegate.getJoinDebateMod();
	}

	@Override
	public Integer getJoinWarMod() {
		return delegate.getJoinWarMod();
	}

	@Override
	public Integer getMDDVMod() {
		return delegate.getMDDVMod();
	}

	@Override
	public Integer getMPDVMod() {
		return delegate.getMPDVMod();
	}

	@Override
	public Integer getMeleeAccuracyMod() {
		return delegate.getMeleeAccuracyMod();
	}

	@Override
	public Integer getMeleeDamageMod() {
		return delegate.getMeleeDamageMod();
	}

	@Override
	public Integer getMeleeRateMod() {
		return delegate.getMeleeRateMod();
	}

	@Override
	public Integer getMeleeSpeedMod() {
		return delegate.getMeleeSpeedMod();
	}

	@Override
	public Integer getPDVMod() {
		return delegate.getPDVMod();
	}

	@Override
	public Integer getRangedAccuracyMod() {
		return delegate.getRangedAccuracyMod();
	}

	@Override
	public Integer getRangedDamageMod() {
		return delegate.getRangedDamageMod();
	}

	@Override
	public Integer getRangedRateMod() {
		return delegate.getRangedRateMod();
	}

	@Override
	public Integer getRangedSpeedMod() {
		return delegate.getRangedSpeedMod();
	}
}
