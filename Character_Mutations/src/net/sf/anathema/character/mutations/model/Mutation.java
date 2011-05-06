package net.sf.anathema.character.mutations.model;

import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.library.quality.model.Quality;
import net.sf.anathema.character.library.quality.model.QualityPrerequisite;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualityPredicate;

public class Mutation extends Quality implements IMutation {

	private final int cost;
	
	public Mutation(String id, int cost)
	{
		super(id, mapType(cost));
		this.cost = cost;
	}
	
  public Mutation(String id)
  {
    this(id, 1);
  }
  
  private static MutationType mapType(int cost)
  {
	  switch (cost)
		{
		default:
		case 1:
			return MutationType.Pox;
		case 2:
			return MutationType.Affliction;
		case 4:
			return MutationType.Blight;
		case 6:
			return MutationType.Abomination;
		
		}
  }

  public void accept(IMutationVisitor visitor) {
    // Nothing to do
  }
  
  public int getCost()
  {
	  return cost;
  }

  protected final boolean isPrerequisite(IQuality gift) {
    List<IQualityPredicate> prerequisiteList = getPrerequisiteList();
    for (IQualityPredicate predicate : prerequisiteList) {
      if (predicate instanceof QualityPrerequisite) {
        IQuality[] prerequisiteQualities = ((QualityPrerequisite) predicate).getPrerequisiteQualities();
        return ArrayUtilities.contains(prerequisiteQualities, gift);
      }
    }
    return false;
  }
}