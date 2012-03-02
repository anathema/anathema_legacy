package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.impl.magic.charm.special.AbstractMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.CharmTier;
import net.sf.anathema.character.generic.impl.magic.charm.special.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TieredMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.resources.IResources;

public class SpecialCharmStringBuilder implements IMagicTooltipStringBuilder
{
	private static final String HtmlLineBreak = "<br>"; //$NON-NLS-1$
	private IResources resources;
	
	public SpecialCharmStringBuilder(IResources resources)
	{
		this.resources = resources;
	}
	
	@Override
	public void buildStringForMagic(StringBuilder builder, IMagic magic,
			Object specialDetails)
	{
		if (magic instanceof ICharm && specialDetails instanceof ISpecialCharm)
		{
			ICharm charm = (ICharm)magic;
			ISpecialCharm details = (ISpecialCharm) specialDetails;
			
			if (details instanceof AbstractMultiLearnableCharm)
			{
			    builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases"));
			    builder.append(IMagicTooltipStringBuilder.ColonSpace);
				  if (details instanceof StaticMultiLearnableCharm)
				  {
					  StaticMultiLearnableCharm special = (StaticMultiLearnableCharm) details;
					  builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Static" + special.getAbsoluteLearnLimit()));
				  }
				  if (details instanceof TraitDependentMultiLearnableCharm)
				  {
					  TraitDependentMultiLearnableCharm special = (TraitDependentMultiLearnableCharm) details;
					  builder.append("(");
					  builder.append(resources.getString(special.getTraitType().getId()));
					  if (special.getModifier() != 0) {
						  builder.append(special.getModifier());
					  }
					  builder.append(")");
					  builder.append(IMagicTooltipStringBuilder.Space);
					  builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Times"));
				  }
				  if (details instanceof EssenceFixedMultiLearnableCharm)
					  return;
				  if (details instanceof TieredMultiLearnableCharm)
				  {
					  TieredMultiLearnableCharm special = (TieredMultiLearnableCharm) details;
					  CharmTier[] tiers = special.getTiers();
					  CharmTier first = tiers[0], second = tiers[1], last = tiers[tiers.length - 1];
					  for (CharmTier tier : tiers)
					  {
						  if (tier == first)
							  continue;
						  if (tier == last && tier != second)
						  {
							builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.And"));
							builder.append(IMagicTooltipStringBuilder.Space);
						  }
						  if (tier == second || tiers.length <= 3) {
							builder.append(resources.getString("Essence"));
							builder.append(IMagicTooltipStringBuilder.Space);
						  }
						  builder.append(tier.getEssence());
						  
						  if (tier.getTrait() > 0)
						  {
							builder.append("/");
							if (tier == second) {
								builder.append(resources.getString(charm.getPrimaryTraitType().getId()));
								builder.append(IMagicTooltipStringBuilder.Space);
							}
							builder.append(tier.getTrait());
						  }
						  if (tier != last) {
							builder.append(IMagicTooltipStringBuilder.CommaSpace);
						  }
					  }
				  }
				  builder.append(HtmlLineBreak);
			  }
		}
		
	}
}
