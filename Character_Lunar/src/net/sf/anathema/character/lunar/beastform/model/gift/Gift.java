package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.library.quality.model.Quality;
import net.sf.anathema.character.library.quality.model.QualityPrerequisite;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualityPredicate;

public class Gift extends Quality implements IGift {

	private final int cost;
	
	public Gift(String id, int cost)
	{
		super(id, GiftType.Gift);
		this.cost = cost;
	}
	
  public Gift(String id)
  {
    this(id, 1);
  }

  public void accept(IGiftVisitor visitor) {
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