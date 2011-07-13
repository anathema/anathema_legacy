package net.sf.anathema.character.infernal.template;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.impl.magic.charm.special.Subeffect;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public class WholenessRightlyAssumed implements IMultipleEffectCharm
{
	private final String charmId;
	private final List<ISubeffect> effectList = new ArrayList<ISubeffect>();
	  
	public WholenessRightlyAssumed(String charmId) {
	    this.charmId = charmId;
	  }

	@Override
	public void accept(ISpecialCharmVisitor visitor)
	{
		visitor.visitMultipleEffectCharm(this);		
	}

	@Override
	public String getCharmId() {
		return charmId;
	}
	
	@Override
	public ISubeffect[] buildSubeffects(
			IBasicCharacterData basicCharacterContext,
			ICharmLearnableArbitrator arbitrator, ICharm charm) {
		if (effectList.isEmpty()) {
		      for (Effects effect : Effects.values()) {
		        effectList.add(new Subeffect(effect.getId(), basicCharacterContext, buildLearnCondition(charm, effect, arbitrator)));
		      }
		    }
		    return effectList.toArray(new ISubeffect[effectList.size()]);
	}
	
	private ICondition buildLearnCondition(
			final ICharm charm,
			final Effects effect,
			final ICharmLearnableArbitrator arbitrator)
	{
		return new ICondition()
		{
			@Override
			public boolean isFullfilled()
			{
				if (!arbitrator.isLearnable(charm))
					return false;
				if (effect != Effects.Lethal)
					return true;
				for (ISubeffect subeffect : effectList)
					if (subeffect.getId().equals(Effects.Bashing.getId()) &&
						subeffect.isLearned())
						return true;
				return false;
			}	
		};
	}
	
	private enum Effects
	{
		Crippling, Sickness, Poison, Bashing, Lethal;
		
		public String getId() {
	  	    return name();
	  	  }

	  	  @Override
	  	  public String toString() {
	  	    return name();
	  	  }
	}
}
