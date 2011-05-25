package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public class ArtifactStatsDecorator extends AbstractStats implements IArtifactStats
{
	  private IArtifactStats stats;
	  private ArtifactAttuneType type;
	  private boolean requireAttune;

	  public ArtifactStatsDecorator(IArtifactStats stats, ArtifactAttuneType type, boolean requireAttune) {
	    this.stats = stats;
	    this.type = type;
	    this.requireAttune = requireAttune;
	    setName(stats.getName());
	  }
	  
	  public Integer getAttuneCost()
	  {
		  switch (type)
		  {
		  default:
		  case Unattuned: return 0;
		  case PartiallyAttuned: return stats.getAttuneCost();
		  case FullyAttuned: return stats.getAttuneCost();
		  case UnharmoniouslyAttuned: return 2 * stats.getAttuneCost();
		  }
	  }
	  
		public ArtifactAttuneType getAttuneType()
		{
			return type;
		}
		
	  public boolean allowForeignAttunement()
	  {
		  return true;
	  }
	  
	  public boolean requireAttunementToUse()
	  {
		  return requireAttune;
	  }

	  public IEquipmentStats[] getViews() {
	    return new IEquipmentStats[] { this };
	  }

	  @Override
	  public boolean equals(Object obj) {
	    if (!(obj instanceof ArtifactStatsDecorator)) {
	      return false;
	    }
	    ArtifactStatsDecorator view = (ArtifactStatsDecorator) obj;
	    return view.stats.equals(stats) && view.type == type;
	  }

	  @Override
	  public int hashCode() {
	    return stats.hashCode();
	  }

	  @Override
	  public String getId() {
	    return getName().getId() + "." + type.name(); //$NON-NLS-1$
	  }
}
