package net.sf.anathema.character.generic.framework.magic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.CostStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.HealthCostStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.MagicInfoStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.IResources;

public class GenericCharmStatsCompiler
{
	private final MagicInfoStringBuilder infoBuilder;
	private final IExaltedRuleSet ruleset;
	private final ICharacterType type;
	
	public GenericCharmStatsCompiler(IResources resources, IExaltedRuleSet ruleset, ICharacterType type)
	{
		this.infoBuilder = createMagicInfoStringBuilder(resources);
		this.ruleset = ruleset;
		this.type = type;
	}
	
	public IMagicStats[] compile()
	{
		List<IMagicStats> genericCharmStats = new ArrayList<IMagicStats>();
		ICharm[] charms = CharmCache.getInstance().getCharms(type, ruleset);
		for (ICharm charm : charms)
		{
			if (charm.isGeneric())
			{
				String name = charm.getId().substring(0, charm.getId().lastIndexOf('.'));
				ConfigurableGenericCharmStats stats =
						new ConfigurableGenericCharmStats(
								name,
								charm.getCharacterType(),
								charm.getSource(),
								charm.getCharmTypeModel(),
								infoBuilder.createCostString(charm),
								charm.getDuration(),
								charm.hasAttribute(IExtendedCharmData.COMBO_OK_ATTRIBUTE));
				if (!genericCharmStats.contains(stats))
					genericCharmStats.add(stats);
			}
		}
		return genericCharmStats.toArray(new IMagicStats[0]);
	}
	
	public static MagicInfoStringBuilder createMagicInfoStringBuilder(final IResources resources)
	{
		CostStringBuilder essenceBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.Mote"); //$NON-NLS-1$
	    CostStringBuilder willpowerBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.Willpower"); //$NON-NLS-1$
	    HealthCostStringBuilder healthBuilder = new HealthCostStringBuilder(resources, "CharacterSheet.Charm.HealthLevel"); //$NON-NLS-1$
	    CostStringBuilder experienceBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.ExperiencePoints"); //$NON-NLS-1$
	    return new MagicInfoStringBuilder(resources, essenceBuilder, willpowerBuilder, healthBuilder, experienceBuilder);
	}
}
