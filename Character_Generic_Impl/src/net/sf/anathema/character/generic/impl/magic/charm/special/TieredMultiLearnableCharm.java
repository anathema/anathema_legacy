package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class TieredMultiLearnableCharm extends AbstractMultiLearnableCharm {

  private final CharmTier[] tiers;
  private final ITraitType traitType;
  
  public TieredMultiLearnableCharm(String charmId, CharmTier[] tiers)
  {
	  this (charmId, null, tiers);
  }
  
  public TieredMultiLearnableCharm(String charmId, ITraitType traitType, CharmTier[] tiers)
  {
    super(charmId);
    this.traitType = traitType;
    this.tiers = tiers;
  }
  
  public CharmTier[] getTiers()
  {
	  return tiers;
  }

  public int getAbsoluteLearnLimit() {
    return tiers.length;
  }

  public int getMaximumLearnCount(IGenericTraitCollection traitCollection)
  {
	  int tierLimit = 0;
	  for (CharmTier tier : tiers)
	  {
		  if (tier.getEssence() > 0)
			  if (traitCollection.getTrait(OtherTraitType.Essence).getCurrentValue()
					  < tier.getEssence())
				  break;
		  if (tier.getTrait() > 0)
			  if (traitCollection.getTrait(traitType).getCurrentValue()
					  < tier.getTrait())
				  break;
		  tierLimit++;
	  }
	  return tierLimit;
  }
}