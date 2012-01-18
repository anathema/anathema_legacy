package net.sf.anathema.character.generic.impl.magic.charm.special;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.generic.magic.charms.special.IUpgradableCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class UpgradableCharm extends MultipleEffectCharm implements IUpgradableCharm
{
	private static final int NO_BP_UPGRADE = -1;
	
	List<Upgrade> upgradeList = new ArrayList<Upgrade>();
	private final Map<String, Integer> bpCosts;
	private final Map<String, Integer> xpCosts;
	private final Map<String, Integer> essenceMins;
	private final Map<String, Integer> traitMins;
	private final Map<String, ITraitType> traits;
	private final boolean requiresBase;
	
	public UpgradableCharm(String charmId, String[] effectIds, boolean requiresBase,
			Map<String, Integer> bpCosts, Map<String, Integer> xpCosts,
			Map<String, Integer> essenceMins, Map<String, Integer> traitMins,
			Map<String, ITraitType> traits)
	{
		super(charmId, effectIds);
		this.bpCosts = bpCosts;
		this.xpCosts = xpCosts;
		this.essenceMins = essenceMins;
		this.traitMins = traitMins;
		this.traits = traits;
		this.requiresBase = requiresBase;
	}
	
	  public void accept(ISpecialCharmVisitor visitor) {
		    visitor.visitUpgradableCharm(this);
		  }
	  
	  public ISubeffect[] buildSubeffects(IBasicCharacterData data,
			  IGenericTraitCollection traitCollection,
			  ICharmLearnableArbitrator arbitrator,
			  ICharm charm) {
		if (upgradeList.isEmpty())
	    for (String id : effectIds) {
	      Integer bpCost = bpCosts.get(id);
	      Integer xpCost = xpCosts.get(id);
	      Integer essenceMin = essenceMins.get(id);
	      Integer traitMin = traitMins.get(id);
	      ITraitType trait = traits.get(id);
	      upgradeList.add(new Upgrade(id, data,
	    		  buildLearnCondition(arbitrator, data, traitCollection,
	    				  charm, bpCost != null,
	    				  essenceMin, traitMin, trait),
	    		  bpCost == null ? NO_BP_UPGRADE : bpCost, xpCost));
	    }
	    return upgradeList.toArray(new ISubeffect[upgradeList.size()]);
	  }

	  private ICondition buildLearnCondition(final ICharmLearnableArbitrator arbitrator,
			  final IBasicCharacterData data,
			  final IGenericTraitCollection traitCollection,
			  final ICharm charm,
			  final boolean bpUpgradeAllowed,
			  final Integer essenceMin,
			  final Integer traitMin,
			  final ITraitType trait) {
	    return new ICondition() {
	      public boolean isFulfilled() {
	        boolean learnable = arbitrator.isLearnable(charm) &&
	        	(bpUpgradeAllowed || data.isExperienced());
	        learnable = !learnable ? learnable :
	        	(essenceMin == null ||
	        	 traitCollection.getTrait(OtherTraitType.Essence).getCurrentValue() >= essenceMin);
	        learnable = !learnable ? learnable :
	        	(traitMin == null || trait == null ||
	        	 traitCollection.getTrait(trait).getCurrentValue() >= essenceMin);
	        return learnable;
	        	 
	      }
	    };
	  }
	  
	  public int getUpgradeBPCost()
	  {
		  int total = 0;
		  for (Upgrade upgrade : upgradeList)
			  total += upgrade.isCreationLearned() ? upgrade.getBPCost() : 0;
		  return total;
	  }
	  
	  public int getUpgradeXPCost()
	  {
		  int total = 0;
		  for (Upgrade upgrade : upgradeList)
			  total += upgrade.isLearned() && !upgrade.isCreationLearned() ? upgrade.getXPCost() : 0;
		  return total;
	  }
	  
	  public boolean requiresBase()
	  {
		  return requiresBase;
	  }

	  @Override
	  public boolean equals(Object obj) {
	    return super.equals(obj);
	  }

	  @Override
	  public int hashCode() {
	    return super.hashCode();
	  }
	  
	  private static class Upgrade extends Subeffect
	  {
		  private int bpCost;
		  private int xpCost;
		  
		public Upgrade(String subeffectId, IBasicCharacterData data,
				ICondition learnable, int bpCost, int xpCost) {
			super(subeffectId, data, learnable);
			this.bpCost = bpCost;
			this.xpCost = xpCost;
		}
		
		public int getBPCost()
		{
			return bpCost;
		}
		
		public int getXPCost()
		{
			return xpCost;
		}
	  }
}
