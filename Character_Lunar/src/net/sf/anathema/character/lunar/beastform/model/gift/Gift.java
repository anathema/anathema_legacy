package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.library.quality.model.Quality;
import net.sf.anathema.character.library.quality.model.QualityPrerequisite;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualityPredicate;

public class Gift extends Quality implements IGift {

  public Gift(String id) {
    super(id, GiftType.Gift);
  }

  public void accept(IGiftVisitor visitor) {
    // Nothing to do
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