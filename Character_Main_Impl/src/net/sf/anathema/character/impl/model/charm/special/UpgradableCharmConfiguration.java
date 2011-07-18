package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IUpgradableCharm;
import net.sf.anathema.character.model.charm.special.IUpgradableCharmConfiguration;

public class UpgradableCharmConfiguration extends MultipleEffectCharmConfiguration implements IUpgradableCharmConfiguration
{
	private final IUpgradableCharm upgrade;

	public UpgradableCharmConfiguration(ICharacterModelContext context,
			ICharm charm, IUpgradableCharm visited,
			ICharmLearnableArbitrator arbitrator) {
		super(context, charm, visited, arbitrator);
		upgrade = visited;
	}

	  @Override
	  public void forget() {
	    /*for (ISubeffect effect : getEffects()) {
	      effect.setLearned(false);
	    }*/
	  }

	  @Override
	  public void learn(boolean experienced) {
	    /*if (experienced && getCurrentLearnCount() == 0) {
	      subeffects[0].setExperienceLearned(true);
	    }
	    else if (!experienced && getCreationLearnCount() == 0) {
	      subeffects[0].setCreationLearned(true);
	    }*/
	  }
	  
	public int getUpgradeBPCost()
	{
		return upgrade.getUpgradeBPCost();
	}
	
	public int getUpgradeXPCost()
	{
		return upgrade.getUpgradeXPCost();
	}
}
