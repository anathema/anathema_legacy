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
	    StringBuilder results = new StringBuilder(128);
		  if (details instanceof AbstractMultiLearnableCharm)
		  {
		    results.append(resources.getString("CharmTreeView.ToolTip.Repurchases"));
		    results.append(IMagicStringBuilderConstants.ColonSpace);
			  if (details instanceof StaticMultiLearnableCharm)
			  {
				  StaticMultiLearnableCharm special = (StaticMultiLearnableCharm) details;
				  results.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Static" + special.getAbsoluteLearnLimit()));
			  }
			  if (details instanceof TraitDependentMultiLearnableCharm)
			  {
				  TraitDependentMultiLearnableCharm special = (TraitDependentMultiLearnableCharm) details;
				  results.append("(");
				  results.append(resources.getString(special.getTraitType().getId()));
				  if (special.getModifier() != 0) {
				    results.append(special.getModifier());
				  }
				  results.append(")");
				  results.append(IMagicStringBuilderConstants.Space);
				  results.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Times"));
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
					    results.append(resources.getString("CharmTreeView.ToolTip.Repurchases.And"));
					    results.append(IMagicStringBuilderConstants.Space);
					  }
					  if (tier == second || tiers.length <= 3) {
					    results.append(resources.getString("Essence"));
					    results.append(IMagicStringBuilderConstants.Space);
					  }
					  results.append(tier.getEssence());
					  
					  if (tier.getTrait() > 0)
					  {
					    results.append("/");
						  if (tier == second) {
						    results.append(resources.getString(charm.getPrimaryTraitType().getId()));
						    results.append(IMagicStringBuilderConstants.Space);
						  }
						  results.append(tier.getTrait());
					  }
					  if (tier != last) {
					    results.append(IMagicStringBuilderConstants.CommaSpace);
					  }
				  }
			  }
			  results.append(HtmlLineBreak);
		  }
		  return results.toString();
	}

}
