package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.impl.magic.charm.special.AbstractMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.CharmTier;
import net.sf.anathema.character.generic.impl.magic.charm.special.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TieredMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.resources.IResources;

public class SpecialCharmStringBuilder implements ISpecialCharmStringBuilder
{
	private static final String HtmlLineBreak = "<br>"; //$NON-NLS-1$
	private IResources resources;
	
	public SpecialCharmStringBuilder(IResources resources)
	{
		this.resources = resources;
	}

	@Override
	public String createDetailsString(ICharm charm, ISpecialCharm details)
	{
		  String results = "";
		  if (details instanceof AbstractMultiLearnableCharm)
		  {
			  results = resources.getString("CharmTreeView.ToolTip.Repurchases");
			  results += IMagicStringBuilderConstants.ColonSpace;
			  if (details instanceof StaticMultiLearnableCharm)
			  {
				  StaticMultiLearnableCharm special = (StaticMultiLearnableCharm) details;
				  results += resources.getString("CharmTreeView.ToolTip.Repurchases.Static" + special.getAbsoluteLearnLimit());
			  }
			  if (details instanceof TraitDependentMultiLearnableCharm)
			  {
				  TraitDependentMultiLearnableCharm special = (TraitDependentMultiLearnableCharm) details;
				  results += "(";
				  results += resources.getString(special.getTraitType().getId());
				  if (special.getModifier() != 0)
					  results += special.getModifier();
				  results += ")" + IMagicStringBuilderConstants.Space;
				  results += resources.getString("CharmTreeView.ToolTip.Repurchases.Times");
			  }
			  if (details instanceof EssenceFixedMultiLearnableCharm)
				  return "";
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
						  results += resources.getString("CharmTreeView.ToolTip.Repurchases.And");
						  results += IMagicStringBuilderConstants.Space;
					  }
					  if (tier == second || tiers.length <= 3)
						  results += resources.getString("Essence") + IMagicStringBuilderConstants.Space;
					  results += tier.getEssence();
					  
					  if (tier.getTrait() > 0)
					  {
						  results += "/";
						  if (tier == second)
							  results += resources.getString(charm.getPrimaryTraitType().getId()) + IMagicStringBuilderConstants.Space;
						  results += tier.getTrait();
					  }
					  if (tier != last)
						  results += IMagicStringBuilderConstants.CommaSpace;
				  }
			  }
			  results += HtmlLineBreak;
		  }
		  return results;
	}

}
